package pys.vo;

public class listVO {

	// 코인

	private String coin;

	// 보유수량구하기

	private double amount;

	// 매수평균가 구하기

	private int avg_price;

	// 매수금액 구하기

	private int buy_price;

	// 평가금액 구하기

	private int now_price;

	// 평가손익 구하기

	private double result_p;

	private int result_m;

	public listVO() {
	}

	public listVO(String coin, double amount, int avg_price, int buy_price, int now_price, double result_p,
			int result_m) {
		super();
		this.coin = coin;
		this.amount = amount;
		this.avg_price = avg_price;
		this.buy_price = buy_price;
		this.now_price = now_price;
		this.result_p = result_p;
		this.result_m = result_m;
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

	public int getAvg_price() {
		return avg_price;
	}

	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}

	public int getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}

	public int getNow_price() {
		return now_price;
	}

	public void setNow_price(int now_price) {
		this.now_price = now_price;
	}

	public double getResult_p() {
		return result_p;
	}

	public void setResult_p(double result_p) {
		this.result_p = result_p;
	}

	public int getResult_m() {
		return result_m;
	}

	public void setResult_m(int result_m) {
		this.result_m = result_m;
	}

	@Override
	public String toString() {
		return "listVO [coin=" + coin + ", amount=" + amount + ", avg_price=" + avg_price + ", buy_price=" + buy_price
				+ ", now_price=" + now_price + ", result_p=" + result_p + ", result_m=" + result_m + "]";
	}

}
