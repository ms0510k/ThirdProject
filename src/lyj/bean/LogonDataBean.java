package lyj.bean;

import java.sql.Timestamp;

public class LogonDataBean 
{
	private String name;
	private String id;
	private String passwd;	
	private String phone;
	private String bank;
	private int account;
	private Timestamp reg_date;
	
	public String getName() 
	{ return name; }
	
	public void setName(String name) 
	{ this.name = name; }
	public String getId() 
	
	{ return id; }
	
	public void setId(String id) 
	{ this.id = id; }
	
	public String getPasswd() 
	{ return passwd; }
	
	public void setPasswd(String passwd) 
	{ this.passwd = passwd; }
	
	public String getPhone() 
	{ return phone; }
	
	public void setPhone(String phone) 
	{ this.phone = phone; }
	
	public String getBank() 
	{ return bank; }
	
	public void setBank(String bank) 
	{ this.bank = bank; }
		
	public int getAccount() 
	{ return account; }
	
	public void setAccount(int account) 
	{ this.account = account; }
	
	public Timestamp getReg_date() 
	{ return reg_date; }
	
	public void setReg_date(Timestamp reg_date)
	{ this.reg_date = reg_date; }	

}