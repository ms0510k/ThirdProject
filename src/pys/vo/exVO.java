package pys.vo;

public class exVO {
	private int memnum;
	private String excoin;
	private int exmoney;
	private double examount;
	
	
	
	public exVO() {
	}

	public exVO(int memnum,  String excoin, int exmoney, double examount) {
		this.memnum = memnum;
		this.excoin = excoin;
		this.exmoney = exmoney;
		this.examount =	examount;
	}

	


	public int getMemnum() {
		return memnum;
	}

	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}

	public String getExcoin() {
		return excoin;
	}

	public void setExcoin(String excoin) {
		this.excoin = excoin;
	}

	public int getExmoney() {
		return exmoney;
	}

	public void setExmoney(int exmoney) {
		this.exmoney = exmoney;
	}

	public double getExamount() {
		return examount;
	}

	public void setExamount(double examount) {
		this.examount = examount;
	}

	@Override
	public String toString() {
		return "exVO [memnum=" + memnum + ", excoin=" + excoin + ", exmoney=" + exmoney
				+ ", examount=" + examount + "]";
	}

	
	
	
	
}
