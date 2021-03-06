package com.depot.ex.admin.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.depot.ex.admin.dto.CouponData;
import com.depot.ex.admin.dto.FormData;
import com.depot.ex.admin.entity.Coupon;
import com.depot.ex.admin.entity.Depotcard;
import com.depot.ex.admin.entity.IllegalInfo;
import com.depot.ex.admin.entity.Income;
import com.depot.ex.admin.entity.ParkInfo;
import com.depot.ex.admin.entity.Parkinfoall;
import com.depot.ex.admin.entity.User;
import com.depot.ex.admin.service.CouponService;
import com.depot.ex.admin.service.DepotcardService;
import com.depot.ex.admin.service.IllegalInfoService;
import com.depot.ex.admin.service.IncomeService;
import com.depot.ex.admin.service.ParkinfoService;
import com.depot.ex.admin.service.ParkinfoallService;
import com.depot.ex.admin.service.ParkspaceService;
import com.depot.ex.admin.service.UserService;
import com.depot.ex.utils.Constants;
import com.depot.ex.utils.Msg;


@Controller
public class CheckController {

	@Autowired
	private ParkinfoService parkinfoservice;
	@Autowired
	private ParkspaceService parkspaceService;
	@Autowired
	private DepotcardService depotcardService;
	@Autowired 
	private UserService userService;
	@Autowired
	private IllegalInfoService illegalInfoService;
	@Autowired
	private ParkinfoallService parkinfoallService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	private CouponService couponService;
	
	@RequestMapping("/index/check/checkIn")
	@ResponseBody
	@Transactional
	// ????????
	public Msg checkIn(Model model, FormData data) {
		Depotcard depotcard=depotcardService.findByCardnum(data.getCardNum());
		if(data.getParkTem()!=1)
		{
		if(depotcard!=null)
		{
			if(depotcard.getIslose()==1)
			{
				return Msg.fail().add("va_msg", "????????????");
			}
		}else{
			return Msg.fail().add("va_msg", "????????????");
		}
		}
		parkinfoservice.saveParkinfo(data);
		parkspaceService.changeStatus(data.getId(), 1);
		return Msg.success();
	}

	@RequestMapping("/index/check/checkOut")
	@ResponseBody
	@Transactional
	// ??????????0??????1??????????9??????
	public Msg checkOut(Model model, FormData data) {
		int pay_money=data.getPay_money();
		Date parkout=new Date();
		Parkinfoall parkinfoall=new Parkinfoall();
		ParkInfo parkInfo=parkinfoservice.findParkinfoByParknum(data.getParkNum());
		if(data.getPay_type()==9)
		{
			Depotcard depotcard=depotcardService.findByCardnum(data.getCardNum());
			IllegalInfo illegalInfo=illegalInfoService.findByCardnumParkin(data.getCardNum(),parkInfo.getParkin());
			Income income=new Income();
			List<CouponData> coupons=couponService.findAllCouponByCardNum(data.getCardNum(), "");
			if(coupons!=null&&coupons.size()>0)
			{
				couponService.deleteCoupon(coupons.get(0).getId());
			}
			depotcardService.addMoney(data.getCardNum(), 0);
			income.setMoney(pay_money);
			income.setMethod(data.getPayid());
			income.setCardnum(data.getCardNum());
			income.setCarnum(data.getCarNum());
			if(depotcard!=null)
			{
			income.setType(depotcard.getType());
			}
			if(illegalInfo!=null)
			{
				income.setIsillegal(1);
			}
			income.setSource(1);
			income.setTime(parkout);
			Date parkin=parkInfo.getParkin();
			long day=parkout.getTime()-parkin.getTime();
			long time=day/(1000*60);
			if(day%(1000*60)>0){
			time+=1;
			}
			income.setDuration(time);
			incomeService.save(income);
		}else{
			if(data.getPay_type()==9)
			{
				return Msg.fail().add("va_msg", "??????????");
			}else if(data.getPay_type()==0)
			{
				//????????????????????????
				Depotcard depotcard=depotcardService.findByCardnum(data.getCardNum());
				IllegalInfo illegalInfo=illegalInfoService.findByCardnumParkin(data.getCardNum(),parkInfo.getParkin());
				double money=depotcard.getMoney();
				List<CouponData> coupons=couponService.findAllCouponByCardNum(data.getCardNum(), "");
				if(coupons!=null&&coupons.size()>0)
				{
					money-=coupons.get(0).getMoney();
					couponService.deleteCoupon(coupons.get(0).getId());
				}
				money-=pay_money;
				depotcardService.addMoney(depotcard.getCardnum(),money);
				/*Income income=new Income();
				income.setMoney(pay_money);
				income.setMethod(data.getPayid());
				income.setCardnum(data.getCardNum());
				income.setCarnum(data.getCarNum());
				income.setType(depotcard.getType());
				if(illegalInfo!=null)
				{
					income.setIsillegal(1);
				}
				income.setSource(1);
				income.setTime(parkout);*/
				/*Date parkin=parkInfo.getParkin();
				long day=parkout.getTime()-parkin.getTime();
				long time=day/(1000*60);
				if(day%(1000*60)>0){
				time+=1;
				}
				income.setDuration(time);
				income.setTrueincome(1);
				incomeService.save(income);*/
			}else{
				//??????????????
			}
		}
		parkinfoall.setCardnum(parkInfo.getCardnum());
		parkinfoall.setCarnum(parkInfo.getCarnum());
		parkinfoall.setParkin(parkInfo.getParkin());
		parkinfoall.setParknum(data.getParkNum());
		parkinfoall.setParkout(parkout);
		parkinfoall.setParktemp(parkInfo.getParktem());
		parkinfoallService.save(parkinfoall);
		parkspaceService.changeStatusByParkNum(data.getParkNum(),0);
		parkinfoservice.deleteParkinfoByParkNum(data.getParkNum());
		return Msg.success();
	}

	@RequestMapping("/index/check/findParkinfoByParknum")
	@ResponseBody
	// ??????????????????????????
	public Msg findParkinfoByParknum(@RequestParam("parkNum") int parknum) {
		ParkInfo parkInfo = parkinfoservice.findParkinfoByParknum(parknum);
		return Msg.success().add("parkInfo", parkInfo);
	}
	
	@RequestMapping("/index/check/findParkinfoByCardnum")
	@ResponseBody
	// ????????????/????????????????????
	public Msg findParkinfoByCardnum(@RequestParam("cardnum") String cardnum) {
		ParkInfo parkInfo = parkinfoservice.findParkinfoByCardnum(cardnum);
		if(parkInfo!=null)
		{
			return Msg.success().add("parkInfo", parkInfo);
		}
		return Msg.fail();
	}
	
	@RequestMapping("/index/check/findParkinfoDetailByParknum")
	@ResponseBody
	//????????????????????????????
	public Msg findParkinfoDetailByParknum(@RequestParam("parkNum") int parknum)
	{
		ParkInfo parkInfo = parkinfoservice.findParkinfoByParknum(parknum);
		if(parkInfo==null)
		{
			return Msg.fail();
		}
		Date date=parkInfo.getParkin();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String parkin=formatter.format(date);
		System.out.println(parkInfo.toString());
		String cardnum=parkInfo.getCardnum();
		Depotcard depotcard=depotcardService.findByCardnum(cardnum);
		int cardid=0;
		User user =null;
		if(depotcard!=null)
		{
		cardid=depotcard.getId();
		user =userService.findUserByCardid(cardid);
		}
		return Msg.success().add("parkInfo", parkInfo).add("user", user).add("parkin", parkin);
	}
	
	@RequestMapping("/index/check/illegalSubmit")
	@ResponseBody
	//????????
	public Msg illegalSubmit(FormData data,HttpSession httpSession)
	{
		User currentUser=(User) httpSession.getAttribute("user");
		ParkInfo parkInfo=parkinfoservice.findParkinfoByCardnum(data.getCardNum());
		IllegalInfo info=new IllegalInfo();
		IllegalInfo illegalInfo=illegalInfoService.findByCardnumParkin(data.getCardNum(),parkInfo.getParkin());
		if(illegalInfo!=null)
		{
			return Msg.fail().add("va_msg", "????????,????????????");
		}
		info.setCardnum(data.getCardNum());
		info.setCarnum(data.getCarNum());
		info.setIllegalInfo(data.getIllegalInfo());
		info.setUid(currentUser.getId());
		Date date=new Date();
		info.setTime(date);
		info.setParkin(parkInfo.getParkin());
		info.setDelete("N");
		try {
		illegalInfoService.save(info);
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail().add("va_msg", "????????");
		}
		return Msg.success().add("va_msg", "????????");
	}
	
	/*????????????
	 * type:0??????????
	 * type:1????????????????*/
	@RequestMapping("/index/check/ispay")
	@ResponseBody
	public Msg ispay(@RequestParam("parknum") Integer parknum)
	{
		ParkInfo parkInfo=parkinfoservice.findParkinfoByParknum(parknum.intValue());
		Date date=new Date();
		Date parkin;
		long time=0;
		long day=0;
		int illegalmoney=0;
		//??????????10????
		if(parkInfo==null)
		{
			return Msg.fail().add("type", 9);
		}
		//??????????????????
		IllegalInfo illegalInfo=illegalInfoService.findByCarnum(parkInfo.getCarnum(),parkInfo.getParkin());
		if(illegalInfo!=null)
		{
			illegalmoney=Constants.ILLEGAL;
		}
		if(StringUtils.isEmpty(parkInfo.getCardnum()))
		{
			//??????????????????,1????10??
			parkin=parkInfo.getParkin();
			day=date.getTime()-parkin.getTime();
			time=day/(1000*60*60);
			if(day%(1000*60*60)>0){
			time+=1;
			}
			return Msg.success().add("money_pay", time*Constants.TEMPMONEY+illegalmoney).add("va_msg", "????????"+(illegalmoney>0? ",????????"+illegalInfo.getIllegalInfo():""));
		}
		String cardnum=parkInfo.getCardnum();
		Depotcard depotcard=depotcardService.findByCardnum(cardnum);
		//????????8????
		if(depotcard!=null&&depotcard.getType()==1)
		{
			//????????
			double balance=depotcard.getMoney();
			int money=0;
			List<CouponData> coupons=couponService.findAllCouponByCardNum(cardnum, "");
			if(coupons!=null&&coupons.size()>0)
			{
				money=coupons.get(0).getMoney();
			}
			parkin=parkInfo.getParkin();
			day=date.getTime()-parkin.getTime();
			time=day/(1000*60*60);
			if(day%(1000*60*60)>0){
			time+=1;
			}
			if(balance+money-illegalmoney<time*Constants.HOURMONEY)
			{
			return Msg.success().add("money_pay", time*Constants.HOURMONEY+illegalmoney-money-balance).add("va_msg", "????????"+(illegalmoney>0? ",????????"+illegalInfo.getIllegalInfo():""));
			}else{
			return Msg.fail().add("type", 0).add("money_pay", time*Constants.HOURMONEY+illegalmoney-money);
			}
		}
		Date deductedtime=depotcard.getDeductedtime();
		if(depotcard.getType()>1)
		{
		day=date.getTime()-deductedtime.getTime();
		}
		if(depotcard.getType()==3){
			time=day/(1000*60*60*24*30);
		}
		if(depotcard.getType()==4){
			time=day/(1000*60*60*24*365);
		}
		//??????????????????????????????
		if(time<1)
		{
			return Msg.fail().add("type", 1);
		}else{
			//????????????????????????????????????????????????????
			//????????
			double balance=depotcard.getMoney();
			int money=0;
			List<CouponData> coupons=couponService.findAllCouponByCardNum(cardnum, "");
			if(coupons!=null&&coupons.size()>0)
			{
				money=coupons.get(0).getMoney();
			}
			parkin=parkInfo.getParkin();
			day=date.getTime()-parkin.getTime();
			time=day/(1000*60*60);
			if(day%(1000*60*60)>0){
			time+=1;
			}
			if(balance+money-illegalmoney<time*Constants.HOURMONEY)
			{
			return Msg.success().add("money_pay", time*Constants.HOURMONEY+illegalmoney-money-balance).add("va_msg", "????????"+(illegalmoney>0? ",????????"+illegalInfo.getIllegalInfo():""));
			}else{
			return Msg.fail().add("type", 0);
			}
		}
	}

}
