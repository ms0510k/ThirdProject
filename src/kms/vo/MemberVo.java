package kms.vo;

import java.util.Date;

public class MemberVo {
	private int memnum;
	private String name;
	private String email;
	private String pwd;
	private String phone;
	private String bank;
	private int account;
	private Date memdate;
	public MemberVo(int memnum, String name, String email, String pwd, String phone, String bank, int account,
			Date memdate) {
		super();
		this.memnum = memnum;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.phone = phone;
		this.bank = bank;
		this.account = account;
		this.memdate = memdate;
	}
	public int getMemnum() {
		return memnum;
	}
	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public Date getMemdate() {
		return memdate;
	}
	public void setMemdate(Date memdate) {
		this.memdate = memdate;
	}
	@Override
	public String toString() {
		return "MemberVo [memnum=" + memnum + ", name=" + name + ", email=" + email + ", pwd=" + pwd + ", phone="
				+ phone + ", bank=" + bank + ", account=" + account + ", memdate=" + memdate + "]";
	}
}
