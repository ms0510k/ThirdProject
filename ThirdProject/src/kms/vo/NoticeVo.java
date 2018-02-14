package kms.vo;

import java.util.Date;

public class NoticeVo {
	private int notnum;
	private String nottitle;
	private String notcontent;
	private int nothit;
	private Date notdate;
	
public NoticeVo(int notnum, String nottitle, String notcontent, int nothit, Date notdate) {
	super();
	this.notnum = notnum;
	this.nottitle = nottitle;
	this.notcontent = notcontent;
	this.nothit = nothit;
	this.notdate = notdate;
}

public int getNotnum() {
	return notnum;
}

public void setNotnum(int notnum) {
	this.notnum = notnum;
}

public String getNottitle() {
	return nottitle;
}

public void setNottitle(String nottitle) {
	this.nottitle = nottitle;
}

public String getNotcontent() {
	return notcontent;
}

public void setNotcontent(String notcontent) {
	this.notcontent = notcontent;
}

public int getNothit() {
	return nothit;
}

public void setNothit(int nothit) {
	this.nothit = nothit;
}

public Date getNotdate() {
	return notdate;
}

public void setNotdate(Date notdate) {
	this.notdate = notdate;
}

@Override
public String toString() {
	return "NoticeVo [notnum=" + notnum + ", nottitle=" + nottitle + ", notcontent=" + notcontent + ", nothit=" + nothit
			+ ", notdate=" + notdate + "]";
}
}
