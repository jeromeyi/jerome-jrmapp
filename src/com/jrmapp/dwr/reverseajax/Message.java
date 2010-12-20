package com.jrmapp.dwr.reverseajax;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Dec 20, 2010 3:40:45 PM
 * @类说明
 */
public class Message {
	private long id = System.currentTimeMillis();
	private String text;

	public Message(String newtext) {
		this.text = newtext;

		if (this.text.length() > 256) {
			this.text = this.text.substring(0, 256);
		}
	}

	public long getId() {
		return this.id;
	}

	public String getText() {
		return this.text;
	}
}
