package com.upjf.fund.utils.encrpyt;

/**
 * 加密接口
 * 
 * @author wufujing
 * 
 */
public interface IEncrypt {

    public String encrypt(String plainText) throws Exception;

    public String decrypt(String cipherText) throws Exception;

}
