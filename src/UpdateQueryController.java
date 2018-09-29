import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UpdateQueryController {
	
	public void Update(String query) {
	String host = "jdbc:mysql://localhost:3306/shopDB";
	String user = "root";
	String password = "";

	// Try to connect to the database
try {
	
	Class.forName("com.mysql.jdbc.Driver");

	Connection con = (Connection) DriverManager.getConnection(host, user, password);

	Statement stmt = (Statement) con.createStatement();

	int result = stmt.executeUpdate(query);
	
	if(result == 1) {
		
	System.out.println("Query executed successfully!");	
	}else {
		
		System.out.println("There was a Problem executing the query!");
	}
	
	
	//if(result.absolute(numOfColumns)) {
		
	//	System.out.println("Unable to execute query, Record already exists");
		
	//	return;
		
	//}
	
	//System.out.println(result);
	
	con.close();
	
}catch(Exception e) {
	
	System.out.println("An error Occured: " + e);
	
}

}
	
	public PreparedStatement prepareStatement(String query) {
		
		
		//Update(query);
		
		System.out.println(query);
		
		return null;
		
	}
	
	
}
