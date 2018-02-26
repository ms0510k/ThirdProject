package pys.vo;


public class tradeVO {
	
	//거래일자
	private String tdate;
	//코인이름
	private String coin;
	//코인수량
	private int coinamount;
	//거래타입(입금,출금,구매,판매)
	private String tradetype;
	//구입, 판매 가격
	private int tprice;
	//고객번호
	private int memnum;
	
	
	
	
	
	public tradeVO() {
	}





	public tradeVO(String tdate, String coin, int coinamount, String tradetype, int tprice, int memnum) {
		this.coin = coin;
		this.coinamount = coinamount;
		this.tradetype = tradetype;
		this.tprice = tprice;
		this.memnum = memnum;
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





	public void setCoin(String coin) {
		this.coin = coin;
	}





	public int getCoinamount() {
		return coinamount;
	}





	public void setCoinamount(int coinamount) {
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
		return "tradeVO [tdate=" + tdate + ", coin=" + coin + ", coinamount=" + coinamount + ", tradetype=" + tradetype
				+ ", tprice=" + tprice + ", memnum=" + memnum + "]";
	}
	
	
	
}
