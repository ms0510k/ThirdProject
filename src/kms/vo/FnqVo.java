package kms.vo;

public class FnqVo {
	private int fnqnum;
	private String fnqtitle;
	private String fnqcontent;
	private String fnqresult;
	public FnqVo(int fnqnum, String fnqtitle, String fnqcontent, String fnqresult) {
		super();
		this.fnqnum = fnqnum;
		this.fnqtitle = fnqtitle;
		this.fnqcontent = fnqcontent;
		this.fnqresult = fnqresult;
	}
	public int getFnqnum() {
		return fnqnum;
	}
	public void setFnqnum(int fnqnum) {
		this.fnqnum = fnqnum;
	}
	public String getFnqtitle() {
		return fnqtitle;
	}
	public void setFnqtitle(String fnqtitle) {
		this.fnqtitle = fnqtitle;
	}
	public String getFnqcontent() {
		return fnqcontent;
	}
	public void setFnqcontent(String fnqcontent) {
		this.fnqcontent = fnqcontent;
	}
	public String getFnqresult() {
		return fnqresult;
	}
	public void setFnqresult(String fnqresult) {
		this.fnqresult = fnqresult;
	}
	@Override
	public String toString() {
		return "FnqVo [fnqnum=" + fnqnum + ", fnqtitle=" + fnqtitle + ", fnqcontent=" + fnqcontent + ", fnqresult="
				+ fnqresult + "]";
	}
}
