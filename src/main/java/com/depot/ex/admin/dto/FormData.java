package com.depot.ex.admin.dto;

import java.util.Date;


public class FormData {
	//ͣ��λ��ID
	private int id;
	//ͣ��λ��
	private int parkNum;
	//����
	private String cardNum;
	//ͣ������
	private String carNum;
	//�Ƿ���ʱͣ��
	private int parkTem;
	//ͣ��ʱ��
	private Date parkin;
	//����ʱ��
	private Date parkout;
	//��λ����
	private Integer tag;
	//Υ����Ϣ
	private String illegalInfo;
	//֧����ʽ��֧������΢�ţ��ֽ�,�ӿ��п�Ǯ��
	private int payid;
	//֧�����
	private int pay_money;
	//�꿨�¿��Ƿ���
	private int pay_type;
	
	public int getPayid() {
		return payid;
	}
	public void setPayid(int payid) {
		this.payid = payid;
	}
	public int getPay_money() {
		return pay_money;
	}
	public void setPay_money(int pay_money) {
		this.pay_money = pay_money;
	}
	public int getPay_type() {
		return pay_type;
	}
	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}
	public String getIllegalInfo() {
		return illegalInfo;
	}
	public void setIllegalInfo(String illegalInfo) {
		this.illegalInfo = illegalInfo;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParkNum() {
		return parkNum;
	}
	public void setParkNum(int parkNum) {
		this.parkNum = parkNum;
	}
	public Date getParkout() {
		return parkout;
	}
	public void setParkout(Date parkout) {
		this.parkout = parkout;
	}
	public Date getParkin() {
		return parkin;
	}
	public void setParkin(Date parkin) {
		this.parkin = parkin;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public int getParkTem() {
		return parkTem;
	}
	public void setParkTem(int parkTem) {
		this.parkTem = parkTem;
	}
	@Override
	public String toString() {
		return "FormData [id=" + id + ", parkNum=" + parkNum + ", cardNum=" + cardNum + ", carNum=" + carNum
				+ ", parkTem=" + parkTem + ", parkin=" + parkin + ", parkout=" + parkout + ", tag=" + tag + "]";
	}
	
	
}
