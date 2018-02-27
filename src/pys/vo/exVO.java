package pys.vo;

public class exVO {
	private int exnum;
	private int memnum;
	private String excoin;
	private int exmoney;
	
	
	
	
	public exVO() {
	}

	public exVO(int exnum, int memnum,  String excoin, int exmoney) {
		this.exnum = exnum;
		this.memnum = memnum;
		this.excoin = excoin;
		this.exmoney = exmoney;
	}

	public int getExnum() {
		return exnum;
	}

	public void setExnum(int exnum) {
		this.exnum = exnum;
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

	

	@Override
	public String toString() {
		return "exVO [exnum=" + exnum + ", memnum=" + memnum + ", excoin=" + excoin
				+ ", exmoney=" + exmoney + ", ]";
	}
	
	
	
}
