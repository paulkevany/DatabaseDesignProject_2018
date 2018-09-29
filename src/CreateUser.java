import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CreateUser {

	static String username = "";
	static String email = "";
	static String address = "";
	static String phone = "";

	public void main() {

		try {
			System.out.println("-----Create A New User----- ");

			System.out.println("Enter a Username: ");

			Scanner input = new Scanner(System.in);

			username = input.nextLine();
			
			System.out.println("Enter an email address: ");

			email = input.nextLine();
			
			System.out.println("Enter an address: ");

			address = input.nextLine();

			System.out.println("Enter a phone number: ");

			phone = input.nextLine();
			
			System.out.println("Gathering your data...");

		} catch (Exception e) {
			System.out.println("An error occured gathering the data");

		}

		// This will be used to insert new products into the database

		// I will use prepared statements to make the application more secure.
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shopDB", "root", "");

			PreparedStatement stmt = (PreparedStatement) con.prepareStatement("Insert into User(Username, Email, Address, Phone) VALUES (?, ?, ?, ?)");
			stmt.setString(1, username);// 1 specifies the first parameter in the query
			stmt.setString(2, email);
			stmt.setString(3, address);
			stmt.setString(4, phone);

			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");

			con.close();

		} catch (Exception e) {
			System.out.println("Unable to establish a connection");

		}
	}
}
