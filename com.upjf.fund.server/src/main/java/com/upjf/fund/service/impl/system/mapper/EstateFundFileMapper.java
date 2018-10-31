package com.upjf.fund.service.impl.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.EstateFundFile;

public interface EstateFundFileMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(EstateFundFile record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(EstateFundFile record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    EstateFundFile selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(EstateFundFile record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(EstateFundFile record);

    /**
     * 批量插入
     * @param files
     * @return
     */
	int batchInsertFiles(@Param("files")List<EstateFundFile> files);

	/**
	 * 更改文件状态
	 * @param pid
	 * @param status
	 * @return
	 */
	int updateFileStatus(@Param("pid")String pid, @Param("status")String status);
	
	/**
	 * 根据文件主键批量变更文件状态(伪删除)
	 * @author  lixq 
	 * @param   paramsMap
	 * @return  int  
	 * @date 2018年10月14日
	 */
	int delEstateFundFileByPids(Map<String, Object> paramsMap);
}