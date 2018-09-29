import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class MySQLCon {

	public void main(String query) {

		try {

			String host = "jdbc:mysql://localhost:3306/shopDB";
			String user = "root";
			String password = "";

			// Try to connect to the database

			Class.forName("com.mysql.jdbc.Driver");

			Connection con = (Connection) DriverManager.getConnection(

					host, user, password);

			Statement stmt = (Statement) con.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			
			
			

			int numOfColumns = rsmd.getColumnCount(); // Will get the number of columns
			

			
			//if(rs.absolute(numOfColumns)) {
				
				//System.out.println("Unable to execute query, Record already exists");
				
				//return;
				
			//}
			String name;
			String attribute;

			while (rs.next()) {
				System.out.println("------------------------------");
				for (int i = 1; i <= numOfColumns; i++) {
					name = rsmd.getColumnLabel(i);
					attribute = rs.getString(i);
					
					System.out.println(name + ": " + attribute);
					
					
				}
				
			//	System.out.println("-----------------");
				

			}

			con.close(); //Close the connection after

		} catch (Exception e) {

			System.out.println(e);
			

		}
		

	}

}
