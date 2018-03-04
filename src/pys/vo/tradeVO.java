package pys.vo;


public class tradeVO {
	
	private int tnum;
	private String tdate;
	private String coin;
	private double coinamount;
	private String tradetype;
	private int tprice;
	private int memnum;
	private int fee;
	
	
	
	
	public tradeVO() {
	}





	public tradeVO( int tnum,String tdate, String coin, double coinamount, String tradetype, int tprice, int memnum,int fee) {
		this.tnum = tnum;
		this.tdate = tdate;
		this.coin = coin;
		this.coinamount = coinamount;
		this.tradetype = tradetype;
		this.tprice = tprice;
		this.memnum = memnum;
		this.fee = fee;
	}



	
	


	public int getTnum() {
		return tnum;
	}





	public void setTnum(int tnum) {
		this.tnum = tnum;
	}





	public String getTdate() {
		return tdate;
	}





	public void setTdate(String tdate) {
		this.tdate = tdate;
	}





	public String getCoin() {
		return coin;
	}


	
	


	public int getFee() {
		return fee;
	}




	public void setFee(int fee) {
		this.fee = fee;
	}





	public void setCoin(String coin) {
		this.coin = coin;
	}





	




	public double getCoinamount() {
		return coinamount;
	}





	public void setCoinamount(double coinamount) {
		this.coinamount = coinamount;
	}





	public String getTradetype() {
		return tradetype;
	}





	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}





	public int getTprice() {
		return tprice;
	}





	public void setTprice(int tprice) {
		this.tprice = tprice;
	}





	public int getMemnum() {
		return memnum;
	}





	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}





	@Override
	public String toString() {
		return "tradeVO [tnum=" + tnum + ", tdate=" + tdate + ", coin=" + coin + ", coinamount=" + coinamount
				+ ", tradetype=" + tradetype + ", tprice=" + tprice + ", memnum=" + memnum + ", fee=" + fee + "]";
	}





	




	
	
	
}
