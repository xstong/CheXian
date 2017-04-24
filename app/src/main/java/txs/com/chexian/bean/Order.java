package txs.com.chexian.bean;

import java.util.Date;

public class Order {
	private int ord_id;
	private String ord_userName;
	private String ord_carNo;
	private String ord_phone;
	private String ord_company;
	private double ord_price;
	private String ord_gift;
	private String ord_remark;
	private User ord_user;
	private Salesman ord_salesman;
	private Date ord_createDate;
	private int ord_state;
	public int getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}
	public String getOrd_userName() {
		return ord_userName;
	}
	public void setOrd_userName(String ord_userName) {
		this.ord_userName = ord_userName;
	}
	public String getOrd_carNo() {
		return ord_carNo;
	}
	public void setOrd_carNo(String ord_carNo) {
		this.ord_carNo = ord_carNo;
	}
	public String getOrd_phone() {
		return ord_phone;
	}
	public void setOrd_phone(String ord_phone) {
		this.ord_phone = ord_phone;
	}
	public String getOrd_company() {
		return ord_company;
	}
	public void setOrd_company(String ord_company) {
		this.ord_company = ord_company;
	}
	public double getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(double ord_price) {
		this.ord_price = ord_price;
	}
	public String getOrd_gift() {
		return ord_gift;
	}
	public void setOrd_gift(String ord_gift) {
		this.ord_gift = ord_gift;
	}
	public String getOrd_remark() {
		return ord_remark;
	}
	public void setOrd_remark(String ord_remark) {
		this.ord_remark = ord_remark;
	}
	public User getOrd_user() {
		return ord_user;
	}
	public void setOrd_user(User ord_user) {
		this.ord_user = ord_user;
	}
	public Salesman getOrd_salesman() {
		return ord_salesman;
	}
	public void setOrd_salesman(Salesman ord_salesman) {
		this.ord_salesman = ord_salesman;
	}
	public Date getOrd_createDate() {
		return ord_createDate;
	}
	public void setOrd_createDate(Date ord_createDate) {
		this.ord_createDate = ord_createDate;
	}
	public int getOrd_state() {
		return ord_state;
	}
	public void setOrd_state(int ord_state) {
		this.ord_state = ord_state;
	}
	
	
}
