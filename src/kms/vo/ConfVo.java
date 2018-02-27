package kms.vo;

import java.util.Date;

public class ConfVo {
	private int connum;
	private String email;
	private int outmoney;
	private String confirm;
	private Date condate;
	public ConfVo(int connum, String email, int outmoney, String confirm, Date condate) {
		super();
		this.connum = connum;
		this.email = email;
		this.outmoney = outmoney;
		this.confirm = confirm;
		this.condate = condate;
	}
	public int getConnum() {
		return connum;
	}
	public void setConnum(int connum) {
		this.connum = connum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getOutmoney() {
		return outmoney;
	}
	public void setOutmoney(int outmoney) {
		this.outmoney = outmoney;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public Date getCondate() {
		return condate;
	}
	public void setCondate(Date condate) {
		this.condate = condate;
	}
	@Override
	public String toString() {
		return "ConfVo [connum=" + connum + ", email=" + email + ", outmoney=" + outmoney + ", confirm=" + confirm
				+ ", condate=" + condate + "]";
	}
}
