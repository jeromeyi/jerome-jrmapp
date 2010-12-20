package com.jrmapp.dwr.reverseajax;

import java.util.LinkedList;

import org.directwebremoting.Browser;
import org.directwebremoting.ui.dwr.Util;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Dec 20, 2010 3:37:45 PM
 * @类说明
 */
public class JavaChat {

	  private final LinkedList<Message> messages = new LinkedList();

	  public void addMessage(String text)
	  {
	    if ((text != null) && (text.trim().length() > 0))
	    {
	      this.messages.addFirst(new Message(text));
	      while (this.messages.size() > 10)
	      {
	        this.messages.removeLast();
	      }

	    }

	    Util.setValue("text", "");

	    Browser.withCurrentPage(new Runnable()
	    {
	      public void run()
	      {
	        Util.removeAllOptions("chatlog");
	        Util.addOptions("chatlog", JavaChat.this.messages, "text");
	      }
	    });
	  }
	}