package com.upjf.fund.service.impl.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.PaymentAccessory;
import com.upjf.fund.dto.ReceivedAccessory;
import com.upjf.fund.service.impl.business.mapper.PaymentAccessoryMapper;
import com.upjf.fund.service.impl.business.mapper.ReceivedAccessoryMapper;
import com.upjf.fund.service.impl.system.mapper.EstateFundFileMapper;
import com.upjf.fund.service.system.EstateFundFileService;
import com.upjf.fund.utils.UuidGenerator;

@Service("estateFundFileService")
public class EstateFundFileServiceImpl implements EstateFundFileService{
	
	@Autowired
	private EstateFundFileMapper estateFundFileMapper;
	
	@Autowired
	private PaymentAccessoryMapper paymentAccessoryMapper;
	
	@Autowired
	private ReceivedAccessoryMapper receivedAccessoryMapper;

	@Override
	@Transactional
	public int batchInsertFiles(List<EstateFundFile> files) {
	
		
		return this.estateFundFileMapper.batchInsertFiles(files);
	}

	@Override
	@Transactional
	public int delete(String pid) {
		
		return this.estateFundFileMapper.deleteByPrimaryKey(pid);
	}

	@Override
	@Transactional
	public int batchInsertPaymentAccessory(List<EstateFundFile> files, String paymentAccessoryReceive,String paymentId) {
		List<PaymentAccessory> accessoryList = new ArrayList<PaymentAccessory>();
		for(EstateFundFile file: files){
			PaymentAccessory accessory = new PaymentAccessory();
			accessory.setPid(UuidGenerator.getUuidGenerator());
			accessory.setFileId(file.getPid());
			accessory.setPaymentId(paymentId);
			accessory.setAccessoryType(paymentAccessoryReceive);
			accessory.setCreateDate(new Date());
			accessoryList.add(accessory);
		}
		return this.paymentAccessoryMapper.batchInsertPaymentAccessory(accessoryList);
		
	}

	@Override
	public List<Map<String, Object>> getPaymentAccessoriesByPaymentId(String paymentId, Integer offset, Integer pageRows) {
		
		return this.paymentAccessoryMapper.getPaymentAccessoriesByPaymentId(paymentId,offset,pageRows);
	}

	@Override
	public Integer countPaymentAccessoriesByPaymentId(String paymentId) {
		
		return this.paymentAccessoryMapper.countPaymentAccessoriesByPaymentId(paymentId);
	}

	@Override
	@Transactional
	public int updateFileStatus(String pid, String status) {
		
		return this.estateFundFileMapper.updateFileStatus(pid,status);
	}

	@Override
	public List<Map<String, Object>> getPaybackAccessoriesByPaybackId(String paybackId, Integer offset, Integer pageRows) {
		
		return this.receivedAccessoryMapper.getPaybackAccessoriesByPaybackId(paybackId,offset,pageRows);
	}

	@Override
	public Integer countPaybackAccessoriesByPaybackId(String paybackId) {
		
		return this.receivedAccessoryMapper.countPaybackAccessoriesByPaybackId(paybackId);
	}

	@Override
	@Transactional
	public int batchInsertPaybackAccessory(List<EstateFundFile> files,String accessoryType, String paybackId) {
		List<ReceivedAccessory> accessoryList = new ArrayList<ReceivedAccessory>();
		for(EstateFundFile file: files){
			ReceivedAccessory accessory = new ReceivedAccessory();
			accessory.setPid(UuidGenerator.getUuidGenerator());
			accessory.setFileId(file.getPid());
			accessory.setReceivedId(paybackId);
			accessory.setAccessoryType(accessoryType);
			accessory.setCreateDate(new Date());
			accessoryList.add(accessory);
		}
		return this.receivedAccessoryMapper.batchInsertPaybackAccessory(accessoryList);
	}

}
