package pys.vo;

public class exVO {
	//�ŷ���ȣ
	private int exnum;
	//����ȣ
	private int memnum;
	//�ŷ�Ÿ��
	private String buysell;
	//�����̸�
	private String excoin;
	//���� ���� �����ݾ�
	private int exmoney;
	//���� �ŷ�����
	private String exdate;
	
	
	
	
	public exVO() {
	}

	public exVO(int exnum, int memnum, String buysell, String excoin, int exmoney, String exdate) {
		this.exnum = exnum;
		this.memnum = memnum;
		this.buysell = buysell;
		this.excoin = excoin;
		this.exmoney = exmoney;
		this.exdate = exdate;
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

	public String getBuysell() {
		return buysell;
	}

	public void setBuysell(String buysell) {
		this.buysell = buysell;
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

	public String getExdate() {
		return exdate;
	}

	public void setExdate(String exdate) {
		this.exdate = exdate;
	}

	@Override
	public String toString() {
		return "exVO [exnum=" + exnum + ", memnum=" + memnum + ", buysell=" + buysell + ", excoin=" + excoin
				+ ", exmoney=" + exmoney + ", exdate=" + exdate + "]";
	}
	
	
	
}
