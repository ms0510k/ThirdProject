package kms.vo;

import java.util.Date;

public class ConfVo {
	private int connum;
	private String email;
	private String confirm;
	private Date condate;
	public ConfVo(int connum, String email, String confirm, Date condate) {
		super();
		this.connum = connum;
		this.email = email;
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
		return "ConfVo [connum=" + connum + ", email=" + email + ", confirm=" + confirm + ", condate=" + condate + "]";
	}
}
