package com.wp.toeic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Date nowTime = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy³â/MM¿ù/ddÀÏ");

		
		JspContext context = getJspContext();
		context.setAttribute("date_list", nowTime);
		
		JspFragment body = getJspBody();
		body.invoke(null);
		
	}
	
}
