package com.jrmapp.action;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.ui.dwr.Util;
import org.springframework.stereotype.Controller;

import com.jrmapp.dwr.reverseajax.Message;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Jan 5, 2011 2:45:54 PM
 * @类说明
 */
@Controller("dwrAction")
//@Scope("prototype")
public class DWRAction extends ActionSupport implements ServletContextAware,
		java.io.Serializable {
	protected ServletContext servletContext;

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}
	 private final LinkedList<Message> messages = new LinkedList();
	public void outMessage(String msg) {
		
		System.out.println("go====");
		ServerContext wctx = ServerContextFactory.get(this.servletContext);
		ScriptSession scriptSessions = null;
		Collection<ScriptSession> sessions = wctx.getAllScriptSessions();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		msg = sdf.format(new Date()) + " \t" + msg;
		ScriptBuffer script = new ScriptBuffer();
		script.appendScript("showMessage(").appendData(msg).appendScript(");");

		for (ScriptSession session : sessions) {
			session.addScript(script);
		}
		
	/*	System.out.println("go===="+text);
	    if ((text != null) && (text.trim().length() > 0))
	    {
	      this.messages.addFirst(new Message(text));
	      while (this.messages.size() > 10)
	      {
	        this.messages.removeLast();
	      }

	    }

	    Util.setValue("text", "");
	    //WebContextFactory.get().getScriptSession().setAttribute("userid", "11"); 
	    String page = ServerContextFactory.get().getContextPath() + "/java-chat.html"; 
	    System.out.println(page);
	    Browser.withPage(page,new Runnable()
	    {
	      public void run()
	      {
	        Util.removeAllOptions("chatlog");
	        Util.addOptions("chatlog", DWRAction.this.messages, "text");
	      }
	    });*/
		
	}
	
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
	        Util.addOptions("chatlog", DWRAction.this.messages, "text");
	      }
	    });
	  }
}
