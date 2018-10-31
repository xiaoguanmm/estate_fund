package com.upjf.fund.constants;

/**
 * 标签CODE常量
 * @author guantong
 *
 */
public interface TagConstants {
	
/***************************字典常量 START***********************************************/
	/**
	 * 状态
	 */
	public static final String DICT_STATUS = "STATUS";
	
	/**
	 * 权限类型
	 */
	public static final String AUTHORITY_TYPE = "AUTHORITY_TYPE";
	
	/**
	 * 企业所有制性质,通过该code可以获取字典表和字典详情信息表中关于企业所有制性质的类型
	 */
	public static final String CORPORATION_PROPERTY = "CORPORATION_PROPERTY";
	
	/**
	 * 银行信息,通过该code可以获取字典表及字典详情信息表中各个银行信息
	 */
	public static final String BANK_NAME = "BANK_NAME";
	
	/**
	 * 项目业态组成类型,通过该code可以获取到字典表及字典详情表中关于的项目业态组成类型.
	 */
	public static final String BUSINESS_COMPOSITION = "BUSINESS_COMPOSITION";
	
	/**
	 * 项目种类,通过该code可以 获取到字典表及字典详情表中关于项目种类的相关信息
	 */
	public static final String PROJECT_CATEGORY = "PROJECT_CATEGORY";
	
	/**
	 * 土地获取方式,通过该code可以获取字典表及字典详情信息表中关于土地获取方式的各个类型信息
	 */
	public static final String LAND_GET_WAY = "LAND_GET_WAY";
	
	/**
	 * 项目进度状态,通过该code可以获取到字典表及字典详情表中关于项目进度状态的各个类型值
	 */
	public static final String PROJECT_PROGRESS = "PROJECT_PROGRESS";
	
	/**
	 * 股东类别
	 */
	public static final String STOCKHOLDER_TYPE = "STOCKHOLDER_TYPE";
	
	/**
	 * 出资类别
	 */
	public static final String CONTRIBUTIVE_TYPE = "CONTRIBUTIVE_TYPE";
	
	/**
	 * 证载建面
	 */
	public static final String CERT_BUILD_AREA = "CERT_BUILD_AREA";
	
	/**
	 * 企业资料扫描件类型
	 */
	public static final String CORPORATION_DATA_TYPE = "CORPORATION_DATA_TYPE";
	
	
/***************************字典常量 END***********************************************/

	
	
/***************************系统地区常量 START***********************************************/
	
	/**
	 * 省级编码,通过该code可以获取到系统地区信息表中省级信息
	 */
	public static final String PROVINCE_CODE = "1";
	
	/**
	 * 市级编码,通过该code可以获取到系统地区信息表中市级信息
	 */
	public static final String CITY_CODE = "2";
	
	/**
	 * 区级编码,通过该code可以获取到系统地区信息表中区级信息
	 */
	public static final String REGION_CODE = "3";
	
	
/***************************系统地区常量 END***********************************************/	

	
	
/***************************Json数据响应参数常量 START***********************************************/
	
	/**
	 * 响应的编码参数名称,值为s(操作成功),e(操作失败).
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 响应的成功或者失败的信息说明名称,所有的成功一否信息都会存在该值中键值对存储
	 */
	public static final String MSG = "msg";
	
	/**
	 * 表示请求成功
	 */
	public static final String SUCCESS_CODE_S = "s";
	
	/**
	 * 表示操作失败
	 */
	public static final String SUCCESS_CODE_E = "e";
	
/***************************Json数据响应参数常量 END***********************************************/
	
	
/***************************系统服务层响应参数常量 START***********************************************/
	
	/**
	 * 存在相同的标识
	 */
	public static final int EXIST_CODE = -1;


/***************************系统服务层响应参数常量 END***********************************************/
	
	
	/**
	 * 企业信息
	 */
	public static final String ENTERPRISE_TYPE ="1";
	/**
	 * 项目公司信息
	 */
	public static final String PROJECT_ENTERPRISE_TYPE ="2";
	/**
	 * 股东信息
	 */
	public static final String STOCK_TYPE ="3";
	
	/**
	 * 项目名称
	 */
	public static final String PROJECT_NAME ="PROJECT_NAME";
	
	/**
	 * 菜单
	 */
	public static final String MENU = "MENU";
	
	/**
	 * 分红方式
	 */
	public static final String DIVIDEND_TYPE = "DIVIDEND_TYPE";
	
	/**系统权限*/
	public static final String AUTHORITY = "AUTHORITY";

}
