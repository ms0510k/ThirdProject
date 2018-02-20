package kms.vo;

import java.util.Date;

public class CompVo {
	private int comnum;
	private int memnum;
	private String comtitle;
	private String email;
	private String comcontent;
	private String comresult;
	private int comhit;
	private Date comdate;
	public CompVo(int comnum, int memnum, String comtitle, String email, String comcontent, String comresult, int comhit,
			Date comdate) {
		super();
		this.comnum = comnum;
		this.memnum = memnum;
		this.comtitle = comtitle;
		this.email = email;
		this.comcontent = comcontent;
		this.comresult = comresult;
		this.comhit = comhit;
		this.comdate = comdate;
	}
	public int getComnum() {
		return comnum;
	}
	public void setComnum(int comnum) {
		this.comnum = comnum;
	}
	public int getMemnum() {
		return memnum;
	}
	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}
	public String getComtitle() {
		return comtitle;
	}
	public void setComtitle(String comtitle) {
		this.comtitle = comtitle;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComcontent() {
		return comcontent;
	}
	public void setComcontent(String comcontent) {
		this.comcontent = comcontent;
	}
	public String getComresult() {
		return comresult;
	}
	public void setComresult(String comresult) {
		this.comresult = comresult;
	}
	public int getComhit() {
		return comhit;
	}
	public void setComhit(int comhit) {
		this.comhit = comhit;
	}
	public Date getComdate() {
		return comdate;
	}
	public void setComdate(Date comdate) {
		this.comdate = comdate;
	}
	@Override
	public String toString() {
		return "FnqVo [comnum=" + comnum + ", memnum=" + memnum + ", comtitle=" + comtitle + ", email=" + email
				+ ", comcontent=" + comcontent + ", comresult=" + comresult + ", comhit=" + comhit + "]";
	}
}
