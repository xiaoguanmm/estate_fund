package com.upjf.fund.service.impl.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.PaymentAccessory;
import com.upjf.fund.dto.StockRightsChange;
import com.upjf.fund.dto.StockRightsChangeAccessory;
import com.upjf.fund.dto.StockholderInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.business.StockRightsChangeService;
import com.upjf.fund.service.impl.business.mapper.StockRightsChangeAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.StockRightsChangeMapper;
import com.upjf.fund.utils.UuidGenerator;

@Service("stockRightsChangeService")
public class StockRightsChangeServiceImpl implements StockRightsChangeService {
	
	private static Logger log = LoggerFactory.getLogger(StockRightsChangeServiceImpl.class);
	
	@Autowired
	private StockRightsChangeMapper stockRightsChangeMapper;
	
	@Autowired
	private StockRightsChangeAccessoryMapper stockRightsChangeAccessoryMapper;


	@Override
	@Transactional
	public Map<String,Object> insertStockRightsChange(StockholderInfo stockholderInfo,UserInfo userInfo) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		StockRightsChange stockRightsChange = new StockRightsChange();
		if(null != stockholderInfo) {
			String stockRightsChangePid = UuidGenerator.getUuidGenerator();
			stockRightsChange.setPid(stockRightsChangePid);
			stockRightsChange.setStockholderId(stockholderInfo.getPid());
			stockRightsChange.setCorpId(stockholderInfo.getCorpId());
//			stockRightsChange.setPrjId(prjId);
			stockRightsChange.setStockholderType(stockholderInfo.getStockholderType());
			stockRightsChange.setRegisterCapital(stockholderInfo.getRegisterCapital());
			stockRightsChange.setStockholderContacts(stockholderInfo.getStockholderContacts());
			stockRightsChange.setHoldStockRatio(stockholderInfo.getHoldStockRatio());
			stockRightsChange.setStockRightsStatus(stockholderInfo.getStockRightsStatus());
			stockRightsChange.setStockRightsRemark(stockholderInfo.getStockRightsRemark());
			stockRightsChange.setRemark(stockholderInfo.getRemark());
			
			// 先查询该股东有没有股权变更历史记录，如有则updateDate，没有则createDate
			List<StockRightsChange> stockRightsChangeList = queryStockRightsChangeByStockholderId(stockholderInfo.getPid());
			if(stockRightsChangeList != null && stockRightsChangeList.size() > 0 ) {
				stockRightsChange.setChangeId(userInfo.getPid());
				stockRightsChange.setChangeDate(new Date());
			} else {
				stockRightsChange.setCreateDate(new Date());
				stockRightsChange.setCreateId(userInfo.getPid());
			}
			
			int result = 0;
			result = this.stockRightsChangeMapper.insertSelective(stockRightsChange);
			
			if(result > 0) {
				resultMap.put("stockRightsChangePid", stockRightsChangePid);
				resultMap.put("code", EstateFundConstants.SUCCESS);
				resultMap.put("msg", "保存股权变更历史表成功!");
			} else {
				log.info("保存股权变更历史表失败!");
				resultMap.put("code", EstateFundConstants.ERROR);
				resultMap.put("msg", "保存股权变更历史表失败!");
			}
		}
		return resultMap;
	}
	
	/**
	 * 通过股东表ID查询 股权变更历史表
	 * @return
	 */
	public List<StockRightsChange> queryStockRightsChangeByStockholderId(String stockholderId) {
		return this.stockRightsChangeMapper.queryStockRightsChangeByStockholderId(stockholderId);
	}

	@Override
	public List<Map<String, Object>> queryStockChangeHisList(String stockholderPid, Integer offset,Integer pageRows) {
		return this.stockRightsChangeMapper.queryStockChangeHisList(stockholderPid,offset,pageRows);
	}

	@Override
	public Integer countStockChangeHisList(String stockholderPid) {
		return this.stockRightsChangeMapper.countStockChangeHisList(stockholderPid);
	}

	@Override
	@Transactional
	public Integer batchInsertStockRightsChangeAccessory(List<EstateFundFile> files, String stockRightsChangeId, String loginUserPid) {
		List<StockRightsChangeAccessory> accessoryList = new ArrayList<StockRightsChangeAccessory>();
		for(EstateFundFile file: files){
			StockRightsChangeAccessory accessory = new StockRightsChangeAccessory();
			accessory.setPid(UuidGenerator.getUuidGenerator());
			accessory.setStockRightsChangeId(stockRightsChangeId);
			accessory.setFileId(file.getPid());
			accessory.setCreateDate(new Date());
			accessory.setCreateId(loginUserPid);
			accessoryList.add(accessory);
		}
		return this.stockRightsChangeAccessoryMapper.batchInsertStockRightsChangeAccessory(accessoryList);
	}

	@Override
	public List<Map<String, Object>> getStockChangeAccessoryByStockRightsChangeId(String stockRightsChangeId, Integer offset, Integer pageRows) {
		return this.stockRightsChangeAccessoryMapper.getStockChangeAccessoryByStockRightsChangeId(stockRightsChangeId, offset, pageRows);
	}

	@Override
	public Integer countStockChangeAccessoryByStockRightsChangeId(String stockRightsChangeId) {
		return this.stockRightsChangeAccessoryMapper.countStockChangeAccessoryByStockRightsChangeId(stockRightsChangeId);
	}

}
