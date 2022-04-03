package com.depot.ex.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.depot.ex.admin.entity.Depotcard;
import com.depot.ex.admin.entity.ParkInfo;
import com.depot.ex.admin.entity.User;
import com.depot.ex.admin.service.DepotcardService;
import com.depot.ex.admin.service.ParkinfoService;
import com.depot.ex.admin.service.UserService;
import com.depot.ex.utils.Msg;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DepotcardService depotcardService;
	@Autowired
	private ParkinfoService parkinfoService;
	
	//ajaxУ��username�Ƿ����
		@ResponseBody
		@RequestMapping("/index/user/checkUsername")
		public Msg checkUsername(@RequestParam("username")String username){
			System.out.println("username:"+username);
			User user=userService.findUserByUsername(username);
			if(user==null)
			{
				return Msg.fail().add("va_msg", "�û���������");
			}
			return Msg.success();
		}
		
		//���user
		@ResponseBody
		@RequestMapping("/index/user/addUser")
		public Msg addUser(User user){
			user.setSex("��");
			user.setName(user.getUsername());
			userService.save(user);
			user=userService.findUserByUsername(user.getUsername());
			if(user==null)
			{
				return Msg.fail().add("va_msg", "���ʧ�ܣ�");
			}
				return Msg.success().add("va_msg", "��ӳɹ���");
		}
		
		//����user
		@ResponseBody
		@RequestMapping("/index/user/findUserById")
		public Msg findUserById(@RequestParam("uid")Integer uid,HttpSession httpSession)
		{
			User user=userService.findUserById(uid.intValue());
			if(user==null)
			{
				return Msg.fail().add("va_msg", "����ʧ�ܣ�");
			}else
			{
				User currentUser=(User) httpSession.getAttribute("user");
				return Msg.success().add("user",user).add("role", currentUser.getRole());
			}
			
		}
		
		//�༭user
		@ResponseBody
		@RequestMapping("/index/user/editUser")
		public Msg editUser(User user){
			int uid=user.getId();
			User temUser=userService.findUserById(uid);
			if(user.getRole()==0)
			{
				user.setRole(temUser.getRole());
			}
			user.setPassword(temUser.getPassword());
			user.setCardid(temUser.getCardid());
			try {
						userService.update(user);
			} catch (Exception e) {
				return Msg.fail().add("va_msg", "�޸�ʧ�ܣ�");
			}
				return Msg.success().add("va_msg", "�޸ĳɹ���");
		}
		
		//ɾ��user
		@ResponseBody
		@RequestMapping("/index/user/deleteUser")
		@Transactional
		public Msg deleteUser(@RequestParam("uid") Integer uid)
		{
			User user=userService.findUserById(uid);
			if(user!=null)
			{
				int cardid=user.getCardid();
				if(cardid!=0)
				{
					Depotcard depotcard=depotcardService.findByCardid(cardid);
					String cardnum=depotcard.getCardnum();
					ParkInfo parkInfo=parkinfoService.findParkinfoByCardnum(cardnum);
					//����ͣ������ɾ
					if(parkInfo!=null)
					{
						return Msg.fail().add("va_msg", "�г�����ͣ��������ɾ����");
					}else{
						depotcardService.deleteDepotCard(cardnum);
					}
				}
				userService.delUserById(uid.intValue());
				return Msg.success().add("va_msg", "ɾ���ɹ���");
			}else{
				return Msg.fail().add("va_msg", "ɾ��ʧ�ܣ�");
			}
		}
				
}
