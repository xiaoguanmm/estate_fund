package com.upjf.fund.service.impl.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.PrjCompanyContract;
import com.upjf.fund.service.business.PrjCompanyContractService;
import com.upjf.fund.service.impl.business.mapper.PrjCompanyContractMapper;
import com.upjf.fund.utils.UuidGenerator;

/**
 * 项目公司合同附件服务接口实现类
 * @author zhangcai
 * @date 2018年9月29日
 */
@Service("prjCompanyContractService")
public class PrjCompanyContractServiceImpl implements PrjCompanyContractService {
	
	@Autowired
	private PrjCompanyContractMapper prjCompanyContractMapper;
	
	private static Logger log = LoggerFactory.getLogger(PrjCompanyContractServiceImpl.class);


	/**
	 * 批量插入项目公司合同附件表
	 * @author zhangcai
	 * @param files
	 * @param businessPrjInfoId
	 */
	@Override
	@Transactional
	public int batchInsertPrjCompanyContract(List<EstateFundFile> files,String businessPrjInfoId, String contractName, String loginId) {
		List<PrjCompanyContract> accessoryList = new ArrayList<PrjCompanyContract>();
		for(EstateFundFile file: files){
			PrjCompanyContract prjCompanyContract = new PrjCompanyContract();
			prjCompanyContract.setPid(UuidGenerator.getUuidGenerator());
			prjCompanyContract.setBusinessPrjInfoId(businessPrjInfoId);
			prjCompanyContract.setFileId(file.getPid());
			prjCompanyContract.setContractName(contractName);
			prjCompanyContract.setCreateDate(new Date());
			prjCompanyContract.setCreateId(loginId);
			accessoryList.add(prjCompanyContract);
		}
		return this.prjCompanyContractMapper.batchInsertPrjCompanyContract(accessoryList);
	}


	@Override
	public List<Map<String, Object>> getPrjCompContractByBusPrjInfoId(String businessPrjInfoId, Integer offset, Integer pageRows) {
		return this.prjCompanyContractMapper.getPrjCompContractByBusPrjInfoId(businessPrjInfoId, offset, pageRows);
	}


	@Override
	public Integer countPrjCompContractByBusPrjInfoId(String businessPrjInfoId) {
		return this.prjCompanyContractMapper.countPrjCompContractByBusPrjInfoId(businessPrjInfoId);
	}
	


	
	
}
