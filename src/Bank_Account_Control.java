import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;







public class Bank_Account_Control {
	
	static Scanner scan = new Scanner(System.in);
	


	public static void main(String[] args) {
		
		
		Bank_Account[] user = new Bank_Account[100];
		
		File record = new File("record.txt");
		File temp_record = new File("temp_record.txt");
			
		FileOutputStream user_file = null;
		FileInputStream user_file_read = null;
		ObjectOutputStream objectOutputStream = null;
		ObjectInputStream objectInputStream = null;
		
		BufferedWriter bWriter = null;
		BufferedReader bReader;
		
		
		Integer index = 0;
		
		
		String password = "";
		String str = "";
		String brk = "no";

		String choice = "";
		
		while(!brk.equals("yes")){
			
			System.out.print("Enter the password to login: ");
			password = scan.nextLine();
			

			
			
			if(password.equals("123")){
				brk = "yes";
			}
			
			else{
				
				System.out.println("Wrong Password!");
				
				while(!password.equals("123")){
					
					System.out.print("Enter 1 to try again or 0 to exit: ");
					str = scan.nextLine();
					
					if(str.equals("0")){
						brk = "yes";
						choice = "7";
						break;
					}
					
					else if(str.equals("1")){

						break;
					}
					
					else {
						System.out.println("Invalid");
					}
				}
			}
		}
		
		
		while(!choice.equals("7")) {
			
			String date = "";
			String accountNo = "";
			String name = "";
			String birthDay = "";
			Double age = 0.0;
			String address = "";
			String nid = "";
			String phone = "";
			Double amount = 0.0;
			
			String menu = "";
			
			Integer key = 0;
			
			Boolean mainMenu = false;
			Boolean find = false;
			Integer id = -1;
			String line = "";
			
			
			
			System.out.println("\n\tCUSTOMER ACCOUNT BANKING MANAGEMENT SYSTEM\n");
			System.out.println("\t::::::::: WELCOME TO THE MAIN MENU :::::::::\n");
			
			System.out.println("1. Create New Account");
			System.out.println("2. Update Information of Existing Account");
			System.out.println("3. For Transactions");
			System.out.println("4. Check the Details of Existing Account");
			System.out.println("5. Removing Existing Account");
			System.out.println("6. View Customer's List");
			System.out.println("7. Exit");
			
			System.out.print("Enter your choice: ");
			choice = scan.nextLine();
			
			switch (choice) {
			case "1":
				
				System.out.print("Enter Today's Date (mm/dd/yyyy): ");
				date = scan.nextLine();
				
				
				System.out.print("Enter Account No: ");
				accountNo = scan.nextLine();
				
				Integer chk = 0;
				
				for(int i = 0; i < index; i++){
					if(user[i].accountNo.equals(accountNo)){
						System.out.println("Account no already exist");
						accountNo = "";
						chk = 1;
						break;
					}
				}
				
				if(chk == 1){
					break;
				}
				
				System.out.print("Enter Name: ");
				name = scan.nextLine();
				
				System.out.print("Enter Date of Birth (mm/dd/yyyy): ");
				birthDay = scan.nextLine();
				
				System.out.print("Enter Age: ");
				age = scan.nextDouble();
				
				scan.nextLine();
				
				System.out.print("Enter Address: ");
				address = scan.nextLine();
				
				System.out.print("Enter NID: ");
				nid = scan.nextLine();
				
				System.out.print("Enter Phone: ");
				phone = scan.nextLine();
				
				System.out.print("Enter Amount: ");
				amount = scan.nextDouble();

				scan.nextLine();
				
				
				user[index] = new Bank_Account(date, accountNo, name, birthDay, age, address, nid, phone, amount);
				
				try {
					
					bWriter = new BufferedWriter(new FileWriter(record, true));
					
					bWriter.write(user[index].accountNo);
					bWriter.newLine();
					
					user_file = new FileOutputStream(user[index].accountNo+".txt");
					objectOutputStream = new ObjectOutputStream(user_file);
					objectOutputStream.writeObject(user[index]);
					
					System.out.println("Record Saved in File.");
					System.out.println("Welcome " + user[index].name + "! Your account created Successfully!");
					index++;
					
					
					bWriter.flush();
					bWriter.close();
					user_file.flush();
					user_file.close();
					objectOutputStream.flush();
					objectOutputStream.close();
					
					
					
					
					
				} catch (IOException e) {
					
					System.out.println("error: "+e);
				} 
				
				
				
				
				
				
				
				while(mainMenu.equals(false)){
					
					System.out.print("\nEnter 1 to go to Main Menu or 0 to exit: ");
					menu = scan.nextLine();
					
					if(menu.equals("0")){
						
						choice = "7";
						mainMenu = true;
					}
					
					else if(menu.equals("1")){
						
						mainMenu = true;
					}
					
					else {
						System.out.println("Invalid");
					}
				}
				
				
				
				break;
				
				
				
				
				
				
				
				
			case "2":
				
				
				System.out.print("Enter the Account No: ");
				accountNo = scan.nextLine();
				

				
				try {
					
					bReader = new BufferedReader(new FileReader(record));
					
					
					int i = 0;
					
					
					
					while((line = bReader.readLine()) != null){
						
						
						
						if(line.contains(accountNo)){
							find = true;
							id = i;
							break;
						}
						
						i++;
						
					}
					

					
					
				} catch (IOException e) {
					
					System.out.println("error: "+e);
				}
				
				
				
				
				if(find == true){
					System.out.println("Which Information You Want to Change");
					System.out.println("1. Address");
					System.out.println("2. Phone\n");
					System.out.println("Enter your choice to update: ");
					
					key = scan.nextInt();
					scan.nextLine();
					
					if(key == 1){
						
						System.out.print("Enter the new address: ");
						user[id].address = scan.nextLine();
						
						try {
							
							user_file = new FileOutputStream(user[id].accountNo+".txt");
							objectOutputStream = new ObjectOutputStream(user_file);
							objectOutputStream.writeObject(user[id]);
							
							System.out.println(user[id].name + ", Your adress changed to "+user[id].address);
							
							user_file.flush();
							user_file.close();
							objectOutputStream.flush();
							objectOutputStream.close();
							
						} catch (IOException e) {

							System.out.println("error: "+e);
						}
						
						
						
					}
					
					else if(key == 2){
						
						System.out.println("Enter the new phone no: ");
						user[id].phone = scan.nextLine();
						
						try {
							
							user_file = new FileOutputStream(user[id].accountNo+".txt");
							objectOutputStream = new ObjectOutputStream(user_file);
							objectOutputStream.writeObject(user[id]);
							
							System.out.println(user[id].name + ", Your phone changed to "+user[id].phone);
							
							user_file.flush();
							user_file.close();
							objectOutputStream.flush();
							objectOutputStream.close();
							
						} catch (IOException e) {
							
							System.out.println("error: "+e);
						}
						
						
						
					}
					
					else {
						System.out.println("Wrong Choice");
					}
					
				
				}
				
				
				else {
					System.out.println("No Account Found");
				}
				
				
				while(mainMenu.equals(false)){
					
					System.out.print("\nEnter 1 to go to Main Menu or 0 to exit: ");
					menu = scan.nextLine();
					
					if(menu.equals("0")){
						
						choice = "7";
						mainMenu = true;
					}
					
					else if(menu.equals("1")){
						
						mainMenu = true;
					}
					
					else {
						System.out.println("Invalid");
					}
				}
				
				
				break;
				
				
				
				
				
				
				
				
				
			case "3":
				
				System.out.println("Enter the Account No: ");
				accountNo = scan.nextLine();
				
				
				
				try {
					
					bReader = new BufferedReader(new FileReader(record));
					
					
					int i = 0;
					
					
					
					while((line = bReader.readLine()) != null){
						
						
						
						if(line.contains(accountNo)){
							find = true;
							id = i;
							break;
						}
						
						i++;
						
					}
					

					
					
				} catch (IOException e) {
					
					System.out.println("error: "+e);
				}
				
				

				
				
				if(find == true){
					
					System.out.println("Do you want to");
					System.out.println("1. Deposit?");
					System.out.println("2. Withdraw?");
					System.out.print("Enter your choice: ");
					key = scan.nextInt();
					
					if(key == 1){
						
						System.out.print("Enter Deposit Amount: ");
						Double deposit_amount = scan.nextDouble();
						
						user[id].Deposit(deposit_amount);
						
						try {
							
							user_file = new FileOutputStream(user[id].accountNo+".txt");
							objectOutputStream = new ObjectOutputStream(user_file);
							objectOutputStream.writeObject(user[id]);
							
							System.out.println(user[id].name + ", Your total amount "+user[id].amount);
							
							user_file.flush();
							user_file.close();
							objectOutputStream.flush();
							objectOutputStream.close();
							
						} catch (IOException e) {

							System.out.println("error: "+e);
						}
						
						
					}
					
					else if(key == 2){
						System.out.print("Enter Withdrawal Amount: ");
						Double withdraw_amount = scan.nextDouble();
						
						Integer w = user[id].Withdraw(withdraw_amount);
						
						if(w == 0){
							System.out.println("Withdrawal amount crossed limit");
						}
						
						else{
							
							try {
								
								user_file = new FileOutputStream(user[id].accountNo+".txt");
								objectOutputStream = new ObjectOutputStream(user_file);
								objectOutputStream.writeObject(user[id]);
								
								System.out.println(user[id].name + ", Your total amount "+user[id].amount);
								
								user_file.flush();
								user_file.close();
								objectOutputStream.flush();
								objectOutputStream.close();
								
							} catch (IOException e) {

								System.out.println("error: "+e);
							}
							
							System.out.println("Withdrawal successful");
						}
						
						
					}
				}
				
				else {
					System.out.println("No Account Found");
				}
				
				
				
				while(mainMenu.equals(false)){
					
					System.out.print("\nEnter 1 to go to Main Menu or 0 to exit: ");
					menu = scan.nextLine();
					
					if(menu.equals("0")){
						
						choice = "7";
						mainMenu = true;
					}
					
					else if(menu.equals("1")){
						
						mainMenu = true;
					}
					
					else {
						System.out.println("Invalid");
					}
				}
				
				break;
				
				
				
				
				
			case "4":
				
				System.err.print("Enter the Account No: ");
				accountNo = scan.nextLine();
				
				try {
					
					bReader = new BufferedReader(new FileReader(record));
					
					
					int i = 0;
					
					
					
					while((line = bReader.readLine()) != null){
						
						
						
						if(line.contains(accountNo)){
							find = true;
							id = i;
							break;
						}
						
						i++;
						
					}
					

					
					
				} catch (IOException e) {
					
					System.out.println("error: "+e);
				}
				
				
				if(find == true){
					
					try {
						
						user_file_read = new FileInputStream(user[id].accountNo+".txt");
						objectInputStream = new ObjectInputStream(user_file_read);
						
						Object user_object = objectInputStream.readObject();
						
						user[id] = (Bank_Account) user_object;
						
						System.out.println("Account No: "+user[id].accountNo);
						System.out.println("Name: "+user[id].name);
						System.out.println("Date of Birth: "+user[id].birthDay);
						System.out.println("Age: "+user[id].age);
						System.out.println("Address: "+user[id].address);
						System.out.println("NID: "+user[id].nid);
						System.out.println("Phone: "+user[id].phone);
						System.out.println("Amount: "+user[id].amount);
						
						
						user_file_read.close();
						objectInputStream.close();
						
					} catch (Exception e) {
						
						System.out.println("error: "+e);
					}
					
					
					
					
				}
				
				else {
					System.out.println("No Account Found");
				}
				
				
				
				while(mainMenu.equals(false)){
					
					System.out.print("\nEnter 1 to go to Main Menu or 0 to exit: ");
					menu = scan.nextLine();
					
					if(menu.equals("0")){
						
						choice = "7";
						mainMenu = true;
					}
					
					else if(menu.equals("1")){
						
						mainMenu = true;
					}
					
					else {
						System.out.println("Invalid");
					}
				}
				
				
				break;
				
				
				
				
				
			case "5":
				
				System.err.print("Enter the Account No: ");
				accountNo = scan.nextLine();
				
				try {
					
					bReader = new BufferedReader(new FileReader(record));
					bWriter = new BufferedWriter(new FileWriter(temp_record));
					
					
					int i = 0;
					
					String remove_line = accountNo;
					
					while((line = bReader.readLine()) != null){
						
						
						if(line.equals(remove_line)){
							
							
							
							find = true;
							id = i;
							
							
							
							File tmp = new File(user[id].accountNo+".txt");
							tmp.delete();
							
							
							continue;
						}
						
						bWriter.write(line + System.getProperty("line.separator"));
						
						
						i++;
						
					}
					
					
					bReader.close();
					bWriter.close();
					temp_record.renameTo(record);
					

					
					
				} catch (IOException e) {
					
					System.out.println("error: "+e);
				}
				
				
				
				
				if(find == true){
					
					
					index--;
					
					for(int i = 0; i <= index; i++){
						user[id] = user[id+1];
					}
					
					
					System.out.println("removed");
				}
				
				else {
					System.out.println("remove failed");
				}
				
				
				
				
				while(mainMenu.equals(false)){
					
					System.out.print("\nEnter 1 to go to Main Menu or 0 to exit: ");
					menu = scan.nextLine();
					
					if(menu.equals("0")){
						
						choice = "7";
						mainMenu = true;
					}
					
					else if(menu.equals("1")){
						
						mainMenu = true;
					}
					
					else {
						System.out.println("Invalid");
					}
				}
				
				
				
				break;
				
				
				
				
			case "6":
				
				System.out.println("Account No\t Name\t Address\t Phone\t");
				
				for(int i = 0; i < index; i++){
					System.out.println(user[i].accountNo+"\t"+user[i].name+"\t"+user[i].address+"\t"+user[i].phone);
				}
				
				
				while(mainMenu.equals(false)){
					
					System.out.print("\nEnter 1 to go to Main Menu or 0 to exit: ");
					menu = scan.nextLine();
					
					if(menu.equals("0")){
						
						choice = "7";
						mainMenu = true;
					}
					
					else if(menu.equals("1")){
						
						mainMenu = true;
					}
					
					else {
						System.out.println("Invalid");
					}
				}
				
				
				
				break;
				
				
				

			default:
				
				if(!choice.equals("7")){
					System.out.println("Press 7 to exit\n");
				}
				
				else {
					break;
				}
			}
			
		
			
			
		} 
		
		
		
		record.delete();
		
		System.out.println("The Apllication is create by iRayhan & Id: 011143002");
		
		
		

	}

}
