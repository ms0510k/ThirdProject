package kms.vo;

import java.sql.Date;

public class FeesVo {
	private int feenum;
	private int connum;
	private int feemoney;
	private int memnum;
	private Date feedate;
	public FeesVo(int feenum, int connum, int feemoney, int memnum, Date feedate) {
		super();
		this.feenum = feenum;
		this.connum = connum;
		this.feemoney = feemoney;
		this.memnum = memnum;
		this.feedate = feedate;
	}
	public int getFeenum() {
		return feenum;
	}
	public void setFeenum(int feenum) {
		this.feenum = feenum;
	}
	public int getConnum() {
		return connum;
	}
	public void setConnum(int connum) {
		this.connum = connum;
	}
	public int getFeemoney() {
		return feemoney;
	}
	public void setFeemoney(int feemoney) {
		this.feemoney = feemoney;
	}
	public int getMemnum() {
		return memnum;
	}
	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}
	public Date getFeedate() {
		return feedate;
	}
	public void setFeedate(Date feedate) {
		this.feedate = feedate;
	}
	@Override
	public String toString() {
		return "FeesVo [feenum=" + feenum + ", connum=" + connum + ", feemoney=" + feemoney + ", memnum=" + memnum
				+ ", feedate=" + feedate + "]";
	}
}
