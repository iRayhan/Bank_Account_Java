import java.io.Serializable;

@SuppressWarnings("serial")
public class Bank_Account implements Serializable{
	
	String date;
	String accountNo;
	String name;
	String birthDay;
	Double age;
	String address;
	String nid;
	String phone;
	Double amount;
	
	public Bank_Account(String date, String accountNo, String name, String birthDay, Double age, String address, String nid, String phone, Double amount){
		
		this.date = date;
		this.accountNo = accountNo;
		this.name = name;
		this.birthDay = birthDay;
		this.age = age;
		this.address = address;
		this.nid = nid;
		this.phone = phone;
		this.amount = amount;
	}
	
	public Bank_Account(){
		
	}
	
	
	public Double Deposit(Double deposit_amount){
		
		amount = amount + deposit_amount;
		
		return amount;
		
	}
	
	public Integer Withdraw(Double withdraw_amount){
		
		if(withdraw_amount > amount){
			return 0;
		}
		
		else {
			amount = amount - withdraw_amount;
			
			return 1;
		}
		
	}

}
