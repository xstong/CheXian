package txs.com.chexian.bean;

import java.util.Date;
import java.util.List;

public class Salesman {
	private int sal_id;
	private String sal_salesName;
	private String sal_passWord;
	private String sal_phone;
	private Date sal_createTime;
	private Date sal_lastTime;
	private String sal_lastIP;
	private List<Order> sal_orderList;
	public int getSal_id() {
		return sal_id;
	}
	public void setSal_id(int sal_id) {
		this.sal_id = sal_id;
	}
	public String getSal_salesName() {
		return sal_salesName;
	}
	public void setSal_salesName(String sal_salesName) {
		this.sal_salesName = sal_salesName;
	}
	public String getSal_passWord() {
		return sal_passWord;
	}
	public void setSal_passWord(String sal_passWord) {
		this.sal_passWord = sal_passWord;
	}
	public String getSal_phone() {
		return sal_phone;
	}
	public void setSal_phone(String sal_phone) {
		this.sal_phone = sal_phone;
	}
	public Date getSal_createTime() {
		return sal_createTime;
	}
	public void setSal_createTime(Date sal_createTime) {
		this.sal_createTime = sal_createTime;
	}
	public Date getSal_lastTime() {
		return sal_lastTime;
	}
	public void setSal_lastTime(Date sal_lastTime) {
		this.sal_lastTime = sal_lastTime;
	}
	public String getSal_lastIP() {
		return sal_lastIP;
	}
	public void setSal_lastIP(String sal_lastIP) {
		this.sal_lastIP = sal_lastIP;
	}
	public List<Order> getSal_orderList() {
		return sal_orderList;
	}
	public void setSal_orderList(List<Order> sal_orderList) {
		this.sal_orderList = sal_orderList;
	}
	
	
}
