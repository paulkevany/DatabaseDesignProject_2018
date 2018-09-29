import java.util.Scanner;

import com.mysql.jdbc.Connection;


public class CreateTransaction {
	//MySQLCon con = new MySQLCon();		
	//storeController sc = new storeController();
	UpdateQueryController uc = new UpdateQueryController();
	
	public void newTransaction(int ProductID, int UserID) {
		
		Scanner input = new Scanner(System.in);
		
		String option; 
		
		System.out.println("Would you like to pay now? (y/n)");
		
		option = input.nextLine();
		
		switch(option) {
		
		case "y": 
			
			System.out.println("Processing Transaction...");
			
			uc.Update("INSERT into Transaction (UserID, ProductID, Status) values( " + ProductID + "," + UserID + "," + "'Processing'" +")");
			
			
			break;
			
			
			
			
		default: 
			
			Main.askForNumber();
			
			break;
		
		}
		
		
		
		
		
		
		
		
		
	}

}
