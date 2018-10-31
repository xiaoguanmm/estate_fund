package com.upjf.fund.service.impl.business.mapper;

import com.upjf.fund.dto.ProjectCompany;

public interface ProjectCompanyMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(ProjectCompany record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(ProjectCompany record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    ProjectCompany selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(ProjectCompany record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(ProjectCompany record);
}