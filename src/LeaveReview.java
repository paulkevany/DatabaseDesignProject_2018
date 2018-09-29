import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class LeaveReview {

	int pid;
	int uid;
	MySQLCon query = new MySQLCon();
	String option = "";
	public void insertReview(int productID, int userID) {
		try {
			
		
		
		pid = productID;
		uid = userID;
		
		System.out.println("pid: " + pid);
		System.out.println("uid: " + uid);
		
		System.out.println("productID: " + productID);
		System.out.println("userID: " + userID);
		
		//user pid, uid in query (local variables)
		
		int idEntered = 0;
		
		
		Scanner input = new Scanner(System.in);
		

		System.out.println("\nPlease leave a review for:  ");
		
		query.main("select name from Product WHERE ProductID = " + pid);
		
		
		System.out.println("Review:");
		option = input.nextLine();
		
		
		//System.out.println(option); //Used for testing
		
		}catch(Exception e) {
			
			System.out.println("Error creating your review!");
		}
		
		//Use Prepared statements for leaving a review
		
		
		try {
			
	

			Class.forName("com.mysql.jdbc.Driver");

			Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shopDB", "root", "");

			
			
			PreparedStatement stmt = (PreparedStatement) connect.prepareStatement("Insert into Review(UserID, ProductID, Message) VALUES ( ?, ?, ?)");
			stmt.setInt(1, userID);// 1. set UserID
			stmt.setInt(2, productID);// 1. set ProductID
			stmt.setString(3, option);// 1.set Message
			

			int i = stmt.executeUpdate();
			System.out.println(i + " review added. Thank you!");

			connect.close();

		} catch (Exception e) {
			System.out.println("Unable to establish a connection" + e);

		}
	}
		
}
