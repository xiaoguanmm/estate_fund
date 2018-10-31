package com.upjf.fund.constants;

/**
 * 系统异常信息
 * @author Administrator
 *
 */
public enum I18nConstants {
	/**用户不存在*/
	USER_NOT_EXIST("user.not.exists","用户不存在"),
	/**文件上传失败*/
	FILE_UPLOAD_FAIL("file.upload.fail","文件上传失败"),
	/**删除文件失败*/
	FILE_DELETE_FAIL("file.delete.fail","删除文件失败");
	
	private String code;
	
	private String message;
	

	private I18nConstants(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static String getMessage(I18nConstants i18nConstants) {  
		String code = i18nConstants.getCode();
        for (I18nConstants i18n : I18nConstants.values()) {  
            if (i18n.getCode() == code) {  
                return i18n.message;  
            }  
        }  
        return null;  
    }
	
	public static String getCode(I18nConstants i18nConstants){
		
		return i18nConstants.getCode();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
