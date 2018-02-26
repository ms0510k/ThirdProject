package pys.vo;

public class moneyVO {

	private int exnum;
	private int memnum;
	private String confirm;

	public moneyVO() {
	}

	public moneyVO(int exnum, int memnum, String confirm) {
		this.exnum = exnum;
		this.memnum = memnum;
		this.confirm = confirm;

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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "moneyVO [exnum=" + exnum + ", memnum=" + memnum + ", confirm=" + confirm + "]";
	}

	
	
}
