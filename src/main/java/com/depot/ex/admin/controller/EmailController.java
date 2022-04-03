package com.depot.ex.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.depot.ex.admin.dto.EmailData;
import com.depot.ex.admin.entity.Email;
import com.depot.ex.admin.entity.User;
import com.depot.ex.admin.service.EmailService;
import com.depot.ex.admin.service.UserService;
import com.depot.ex.utils.Msg;


@Controller
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("index/email/addEmail")
	public Msg addEmail(EmailData emailData,HttpSession session)
	{
		Email email=new Email();
		User user=(User) session.getAttribute("user");
		try {
			if(user.getRole()==3)
			{
				email.setTitle(emailData.getTitle());
				email.setContent(emailData.getContent());
				email.setSendid(user.getId());
				email.setToid(0);
				email.setTime(new Date());
			}else{
				Email email2=emailService.findById(emailData.getId());
				emailService.updateManReadById(emailData.getId());
				email.setSendid(user.getId());
				email.setToid(email2.getSendid());
				email.setTitle(emailData.getTitle());
				email.setContent(emailData.getContent());
				email.setTime(new Date());
			}
		} catch (Exception e) {
			return Msg.fail().add("va_msg", "ϵͳ����");
		}
		emailService.addEmail(email);
		return Msg.success().add("va_msg", "��ӳɹ���");
	}
	
	@ResponseBody
	@RequestMapping("index/email/delete")
	public Msg delete(EmailData emailData,HttpSession session)
	{
		User user=(User) session.getAttribute("user");
		return Msg.success();
	}
	
	@ResponseBody
	@RequestMapping("index/email/detail")
	public Msg detail(EmailData emailData,HttpSession session)
	{
		int respon=0;
		User user=(User) session.getAttribute("user");
		Email email=emailService.findById(emailData.getId());
		if(user.getRole()!=3)
		{
			User user1=userService.findUserById(email.getSendid());
			if(email.getManagerisread()<2&&user1.getRole()==3)
			{
				email.setManagerisread(1);
				emailService.updateEmail(email);
				respon=1;
			}
		}else{
			email.setUserisread(1);
			emailService.updateEmail(email);
		}
		return Msg.success().add("email", email).add("respon", respon);
	}
	
	@ResponseBody
	@RequestMapping("index/email/responEmailSubmit")
	public Msg	responEmailSubmit(EmailData emailData,HttpSession session)
	{
		User user=(User) session.getAttribute("user");
		Email email1=emailService.findById(emailData.getId());
		Email email=new Email();
		email.setTitle(emailData.getTitle());
		email.setContent(emailData.getContent());
		email.setTime(new Date());
		email.setToid(email1.getSendid());
		email.setSendid(user.getId());
		emailService.addEmail(email);
		email1.setManagerisread(2);
		emailService.updateEmail(email1);
		return Msg.success();
	}
	@ResponseBody
	@RequestMapping("index/email/deleteEmai")
	public Msg deleteEmai(EmailData emailData,HttpSession session)
	{
		try {
			User user=(User) session.getAttribute("user");
			Email email=emailService.findById(emailData.getId());
			if(user.getRole()!=3)
			{
			email.setManagedelete(1);
			}else{
			email.setUserdeleted(1);
			}
			emailService.updateEmail(email);
		} catch (Exception e) {
			return Msg.fail().add("va_msg", "ɾ��ʧ�ܣ�");
		}
		return Msg.success().add("va_msg", "ɾ���ɹ���");
	}
}
