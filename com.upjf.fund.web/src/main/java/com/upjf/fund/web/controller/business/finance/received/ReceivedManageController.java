package com.upjf.fund.web.controller.business.finance.received;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.Received;
import com.upjf.fund.service.business.ProjectInfoService;
import com.upjf.fund.service.business.ReceivedManageService;
import com.upjf.fund.service.system.EstateFundFileService;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.controller.business.finance.FinanceManageController;
import com.upjf.fund.web.utils.file.upload.FileUpload;

/**
 * 回款管理基类
 * @company upjf.com
 * @author guantong
 *
 */
public class ReceivedManageController extends FinanceManageController {
	
	/**项目股东回款列表页面*/
	protected static final String STOCKHOLDER_RECEIVED_LIST = "finance/received/stockholder_received/stockholder_received_manage";
	
	/**股东回款列表页面(回款历史记录)*/
	protected static final String STOCKHOLDER_RECEIVED_HISTORY_LIST = "finance/received/stockholder_received/stockholder_received_history_manage";
	
	/**投资主体利润分配列表页面*/
	protected static final String INVEST_SUBJECT_RECEIVED_LIST = "finance/received/invest_subject_received/invest_subject_received_manage";
	
	/**投资主体利润分配(回款历史记录)列表页面*/
	protected static final String INVEST_SUBJECT_RECEIVED_HISTORY_LIST = "finance/received/invest_subject_received/invest_subject_received_history_manage";
	
	/**投资人利润分配列表页面*/
	protected static final String INVESTOR_RECEIVED_LIST = "finance/received/investor_received/investor_received_manage";
	
	/**投资人利润分配(回款历史记录)列表页面*/
	protected static final String INVESTOR_RECEIVED_HISTORY_LIST = "finance/received/investor_received/investor_received_history_manage";
	
	@Autowired
	protected ReceivedManageService receivedManageService;
	
	@Autowired
	protected EstateFundFileService estateFundFileService;
	
	@Autowired
	protected ProjectInfoService projectInfoService;
	
	
	/**
	 * 查看回款历史记录
	 * @param payment
	 * @param request
	 * @param response
	 * @return
	 */
	protected String queryHistoryPaybacksList(Received received, HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> rows = this.receivedManageService.getHistoryPaybacksByConditions(received,getOffset(),getPageRows());
		Integer records = this.receivedManageService.countHistoryPaybacksByConditions(received);
		return putJsonData(rows, records);
	}
	
	/**
	 * 新增回款记录
	 * @param received
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelMap addPaybackRecord(Received received,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(received!=null){
			/*对于已完结状态的回款 获取最近的回款日期，新增回款的回款日期不可小于最近的回款日期*/
			if(EstateFundConstants.RECEIVED_STATUS_COMPLATE.equals(received.getReceivedStatus())){
				Date lastReceivedDate = this.receivedManageService.getLastReceivedDate(received);
				if(lastReceivedDate != null && lastReceivedDate.compareTo(received.getReceiverDate())>0){
					mm.put("errMsg", "回款状态已完结，回款时间必须大于等于最近一次的回款时间："+new SimpleDateFormat("yyyy-MM-dd").format(lastReceivedDate));
					return mm;
				}
			}
			if(StringUtils.isNotEmpty(received.getPid())){
				mm.put("pid", received.getPid());
				received.setUpdateId(getUserInfo().getPid());
				received.setUpdateDate(new Date());
				int result = this.receivedManageService.updateReceivedBySelective(received);
				if(result>0){
					mm.put("successMsg", "更新回款信息成功");
				}else{
					mm.put("errMsg", "更新回款信息失败");
					return mm;
				}
			}else{
				String pid = UuidGenerator.getUuidGenerator();
				received.setPid(pid);
				received.setCreateId(getUserInfo().getPid());
				received.setCreateDate(new Date());
				int result = this.receivedManageService.insertPaymentBySelective(received);
				if(result>0){
					mm.put("pid", pid);
					mm.put("successMsg", "新增回款信息成功");
				}else{
					mm.put("errMsg", "新增回款信息失败");
					return mm;
				}
			}
			mm.put("amount", getSatisticsAmountByParams(received));			
			mm.put("received_status",getReceivedStatus(received));
		}else{
			mm.put("errMsg", "回款信息不存在");
		}
		return mm;
	}
	
	/**
	 * 获取回款状态
	 * @param received
	 * @return
	 */
	private Object getReceivedStatus(Received received) {
		
		return this.receivedManageService.getReceivedStatus(received);
	}

	/**
	 * 删除回款记录(逻辑删除只更改状态)
	 * @param prjId
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelMap delPaybackRecord(Received received,HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(received == null || StringUtils.isEmpty(received.getPid())){
			mm.put("errMsg", "信息不存在，删除失败");
			return mm;
		}
		int result = this.receivedManageService.updatePaybackRecordStatus(received.getPid(),EstateFundConstants.STATUS_DELETE);
		if(result>0){
			mm.put("successMsg", "删除回款记录成功");
			mm.put("amount", getSatisticsAmountByParams(received));
			return mm;
		}
		mm.put("errMsg", "删除回款信息失败");
		return mm;
	}
	
	/**
	 * 更新回款合计
	 * @param prjId 项目id
	 * @param receiverId 收款公司
	 * @param receivedType 收款人类型
	 * @return
	 */
	private Map<String,Object> getSatisticsAmountByParams(Received received){

		return this.receivedManageService.statisticsAmountByParmas(received);
	}
	
	
	/**
	 * 获取回款信息附件列表
	 * @param request
	 * @param response
	 * @return
	 */
	protected String queryPaybackAccessoryList(String paybackId,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(paybackId)){
			List<Map<String, Object>> rows = this.estateFundFileService.getPaybackAccessoriesByPaybackId(paybackId,getOffset(),getPageRows());
			Integer records = this.estateFundFileService.countPaybackAccessoriesByPaybackId(paybackId);
			return putJsonData(rows, records);
		}
		return putJsonData(null, 0);
	}
	
	/**
	 * 上传附件
	 * @param paymentId
	 * @param modulePath 模块路径
	 * @param accessoryType 附件类型
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelMap uploadPaybackAccessory(String paymentId,String modulePath,String accessoryType, HttpServletRequest request,HttpServletResponse response){
		ModelMap mm = new ModelMap();
		if(StringUtils.isNotEmpty(paymentId)){
			List<EstateFundFile> files = FileUpload.uploadFile(request, response, modulePath);
			int result = this.estateFundFileService.batchInsertPaybackAccessory(files,accessoryType,paymentId);
			if(files!=null && files.size()>0 && result>0){
				mm.put("successMsg", "上传成功");
				return mm;
			}
		}else{
			mm.put("errMsg", "请先保存数据");
		}
		return mm;
	}

}
