package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.InvestSubjectAccessory;

public interface InvestSubjectAccessoryMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(InvestSubjectAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(InvestSubjectAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    InvestSubjectAccessory selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(InvestSubjectAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(InvestSubjectAccessory record);

    /**
     * 批量插入投资主体附件表
     * @author zhangcai
     * @param accessoryList
     * @return
     */
	int batchInsertInvestSubjectAccessory(@Param("accessoryList") List<InvestSubjectAccessory> accessoryList);

	/**
	 * 通过subjectId查询投资主体附件表
	 * @author zhangcai
	 * @param investSubjectPid
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.15
	 */
	List<Map<String, Object>> getInvestSubjectAccessoryByInvestSubjectId(@Param("investSubjectId") String investSubjectPid, 
			@Param("offset") Integer offset, @Param("pageRows") Integer pageRows);

	/**
	 * 通过subjectId查询投资主体附件表总条数
	 * @author zhangcai
	 * @param investSubjectPid
	 * @return
	 * @date 2018.10.15
	 */
	Integer countInvestSubjectAccessoryByInvestSubjectId(@Param("investSubjectId") String investSubjectPid);
	
}