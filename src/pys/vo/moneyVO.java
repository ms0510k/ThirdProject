package pys.vo;

public class moneyVO {
	
	
	private int memnum;
	private int outmoney;
	private String confirm;

	public moneyVO() {
	}

	public moneyVO(int memnum,int outmoney, String confirm) {
		this.memnum = memnum;
		this.outmoney = outmoney;
		this.confirm = confirm;

	}

	
	public int getMemnum() {
		return memnum;
	}
	
	
	

	public int getOutmoney() {
		return outmoney;
	}

	public void setOutmoney(int outmoney) {
		this.outmoney = outmoney;
	}

	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "moneyVO [memnum=" + memnum + ", outmoney=" + outmoney + ", confirm=" + confirm
				+ "]";
	}


	
	
}
