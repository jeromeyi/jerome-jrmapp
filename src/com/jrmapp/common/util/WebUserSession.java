/*
 * $Id: WebUserSession.java,v 1.1.2.1 2008/03/29 06:28:44 zengjm Exp $
 * Copyright(c) 2000-2008 HC360.COM, All Rights Reserved.
 */
package com.jrmapp.common.util;

import java.io.Serializable;

/**
 * ***��˵��*** ����һ�����ڻ��ǰ̨ϵͳsession����,��Ҫ�Ǵ���ͨ�������֤��õ�����Ϣ���뱾ϵͳ�ĵ�ȷsession
 * 
 * @author zengjuemin
 * @version 1.0 Apr 1, 2008
 * @since 1.0
 * @since 1.0
 */
public class WebUserSession implements Serializable {
	/**��ͨ����*/
	private String gobal_ccp_card;
	/**ͨ�������֤��� 0:�ɹ�;����Ϊ���ɹ�*/
	private String isloginsucess;
	/**
	 * @return Returns the gobal_ccp_card.
	 */
	public String getGobal_ccp_card() {
		return gobal_ccp_card;
	}
	/**
	 * @param gobal_ccp_card The gobal_ccp_card to set.
	 */
	public void setGobal_ccp_card(String gobal_ccp_card) {
		this.gobal_ccp_card = gobal_ccp_card;
	}
	/**
	 * @return Returns the isloginsucess.
	 */
	public String getIsloginsucess() {
		return isloginsucess;
	}
	/**
	 * @param isloginsucess The isloginsucess to set.
	 */
	public void setIsloginsucess(String isloginsucess) {
		this.isloginsucess = isloginsucess;
	}
}
