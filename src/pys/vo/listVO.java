package pys.vo;

public class listVO {

	// 코인

	private String coin;

	// 보유수량구하기

	private double amount;

	
	// 매수금액 구하기

	private double total;


	public listVO() {
	}


	public listVO(String coin, double amount, double total) {
		this.coin = coin;
		this.amount = amount;
		this.total = total;
	}


	public String getCoin() {
		return coin;
	}


	public void setCoin(String coin) {
		this.coin = coin;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}




	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "listVO [coin=" + coin + ", amount=" + amount + ", total=" + total + "]";
	}

	
	
	
	
	

}
