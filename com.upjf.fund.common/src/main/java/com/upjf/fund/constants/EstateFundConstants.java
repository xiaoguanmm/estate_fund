

package com.upjf.fund.constants;

/**
 * 地产基金常量接口
 * @author Administrator
 *
 */
public class EstateFundConstants{
	
	public static String LOGIN_USER = "login_user";
	
	public static String LOGIN_USER_ROLE = "login_user_role";
	
	public static String RESULT_SUCCESS_KEY = "result_success_key";
	
	public static String RESULT_CODE_KEY = "result_code_key";
	
	public static String RESULT_MSG_KEY = "result_msg_key";
	
	public static String SUCCESS = "success";
	
	public static String ERROR = "error";
	
	public static Long EXPIRESS_TIMES = 3600*24*1000L;
	
	//默认系统管理员id
	public static String adminId = "1";
	
	
	public static final String MATH_PLUS = "plus";
	
	public static final String MATH_MINUS = "minus";
	
	public static final String SALT ="estate.fund";
	/**默认每页显示10条*/
	public static final Integer DEFAULT_PAGE_ROWS = 10;
	/**默认显示第一页*/
	public static final String DEFAULT_CUR_PAGE = "1";
	/**默认密码*/
	public static final String DEFALUT_USER_PWD = "111111";
	/**是*/
	public static final String YES = "Y";
	/**否*/
	public static final String NO = "N";
	
	/**状态-正常*/
	public static final String STATUS_NORMAL = "1";
	
	/**状态-正常*/
	public static final String STATUS_DELETE = "0";
	
	/**出资确认状态--确认*/
	public static final String FINANCE_CONFIRM ="1";
	
	/**出资确认状态--未确认*/
	public static final String FINANCE_NOT_CONFIRM ="0";
	
	/**付款凭证*/
	public static final String PAYMENT_ACCESSORY_PAY="0";
	
	/**收款凭证*/
	public static final String PAYMENT_ACCESSORY_RECEIVE="1";
	
	/**付款角色类型-股东*/
	public static final String PAYMENT_TYPE_STOCKHOLDER = "1";
	
	/**付款角色类型-投资主体*/
	public static final String PAYMENT_TYPE_INVEST_SUBJECT = "2";
	
	/**付款角色类型-投资人*/
	public static final String PAYMENT_TYPE_INVESTOR = "3";
	
	/**投资主体*/
	public static final String INVEST_TYPE_INVEST_SUBJECT = "1";
	
	/**投资人*/
	public static final String INVEST_TYPE_INVESTOR="2";
	
	/**BigDecimal 10000*/
	public static final String TEN_THOUSAND = "10000";
	
	/**最大导出条数不可超过10000*/
	public static final String MAX_EXPORT_DATA_NUM = "10000";
	
	/**回款类型 项目股东*/
	public static final String PAYBACK_TYPE_STOCKHOLDER="1";
	/**回款类型 投资主体*/
	public static final String PAYBACK_TYPE_INVEST_SUBJECT="2";
	/**回款类型 投资人*/
	public static final String PAYBACK_TYPE_INVESTOR="3";
	
	/**权限类型菜单*/
	public static final String AUTHORITY_TYPE_MENU = "0";
	/**权限类型增删改*/
	public static final String AUTHORITY_TYPE_OPERATION = "1";
	/**权限类型查询*/
	public static final String AUTHORITY_TYPE_QUERY = "2";
	/**项目状态已完结*/
	public static final String RECEIVED_STATUS_COMPLATE = "1";
	/**项目状态未完结*/
	public static final String RECEIVED_STATUS_NOT_COMPLATE = "2";
	
	
	
	/**
	 * 接口状态返回码
	 */
	public enum MessageCode{
		SUCCESS("0000", "操作成功"),
		FAIL("9999", "请求失败"),
		PROJ_COMPANY_100000("PC100000","无该企业信息"),
		
		C_100000("100000", "场景编号为空"),
		C_100001("100001", "场景密码为空"),
		C_100002("100002", "秘钥为空"),
		C_100003("100003", "接入代码为空"),
		C_100004("100004", "请求号为空"),
		C_100005("100005", "场景用户为空"),
		C_100008("100008", "密码无效"),
		C_200000("200000", "场景不可使用"),
		C_200001("200001", "场景不存在"),
		C_200002("200002", "接入配置不存在"),
		C_200004("200004", "时间限制内！不允许访问！"),
		C_300000("300000", "头信息验证失败"),
		C_300001("300001", "主体报文验证失败！"),
		C_300003("300003", "主体信息为空！"),
		C_300004("300004", "报文体不是json串！"),
		C_300010("300010", "AES解密失败！"),
		C_300011("300011", "RSA解密失败！"),
		C_300012("300012", "RSA解密失败！"),
		C_300013("300013", "RSA加密失败！"),
		C_300014("300014", "AES加密失败！"),
		C_900000("900000", "ip地址未授权"),
		C_999998("999998", "查询失败"),
		C_999999("999999", "其它异常，请联系管理员处理");
		private String code;
		private String msg;
		private MessageCode(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
	
}
