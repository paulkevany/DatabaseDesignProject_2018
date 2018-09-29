import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DatabaseController {

	
	int UserIDEntered = 0;
	int ProductIDEntered = 0;
	
	Scanner input = new Scanner(System.in);
	String option;
	UpdateQueryController uc = new UpdateQueryController();
	MySQLCon con = new MySQLCon();
	Main main = new Main();
	LeaveReview review = new LeaveReview();
	CreateTransaction ct = new CreateTransaction();

	public void getProductsFromDB() {

		con.main("select * from Product");

		// Connection is closed from the MySQLCon class so we won't have unnecessary
		// connections open after were finished with a particular method

		
		
	}

	public void getCustomersFromDB() {
		try {

			con.main("select * from User");
			System.out.println("\n");

		} catch (Exception e) {
			System.out.println("Error Occured");

		}
	}

	public void getCartsFromDB() {

		// Use a join to display a product name
		System.out.println("Here are all the carts:  ");

		
		
	//con.main("select User.Username " + "from User JOIN Cart ON Cart.UserID = User.UserID LIMIT 1");

		
		con.main("select Cart.CartID, Product.ProductID, Name from Product JOIN Cart ON Cart.ProductID = Product.ProductID");
		// Second join to associate the UserID in the Cart with the Users.UserID

		

	}

	public void editProducts() {

		String host = "localhost";
		String user = "root";
		String password = "";
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {

			System.out.println("Driver not found!");
		}

		int idEntered = 0; // To choose the Product to edit
		// Declare Attributes for new values
		String newName;
		String newDescription;
		double newPrice;
		int newQuantity;

		try {
			getProductsFromDB();

			System.out.println("Would you like to edit a product (y/n)?");
			option = input.nextLine();

			switch (option) {

			case "y":

				try {

					if (idEntered < 1) {

						System.out.println("Enter a ProductID to edit/update: ");

						idEntered = input.nextInt();
						input.nextLine(); // Stop from skipping input

						System.out.println("Enter a new Name: ");
						newName = input.nextLine();

						System.out.println("Enter a new Description: ");
						newDescription = input.nextLine();
						System.out.println("Enter a new Price: ");

						newPrice = input.nextDouble();

						// Strip any special characters for security reasons (Protect from sqlInjection)

					//	newName.replaceAll("[^a-zA-Z0-9]", "");
						//newDescription.replaceAll("[^a-zA-Z0-9]", "");
						
						
					//System.out.println("Stripped Variables");
						//System.out.println(newName);
					//	System.out.println(newDescription); //Testing the replace all method

						uc.Update("UPDATE Product SET Name = " + "'"+newName+"'" + ", Description = " + "'"+newDescription+"'" + ", Cost =" + "'"+newPrice+"'" + "where ProductID = '" + idEntered + "'");

					}else {
						
						System.out.println("ProductID must be greater than 0!");
						main.askForNumber();
						
					}

				} catch (Exception e) {

					System.out.println("Unable to update product!");
				}

				main.askForNumber();

				break;

			case "n":

				System.out.println("Would you like to delete a Product?(y/n)");

				option = input.nextLine();

				switch (option) {

				case "y":
					System.out.println("Enter a ProductID to Delete: ");

					idEntered = input.nextInt();

					System.out.println();

					uc.Update("DELETE from Product WHERE ProductID =" + idEntered);

				default:
					main.askForNumber();

				}

			default:

				// new Main().askForNumber();

				break;

			}

		} catch (Exception e) {

			System.out.println("An error has occured: " + e);
		}

	}

	public void editUserInfo() {

		String newUsername;
		String newEmail;

		String newAddress;
		String newPhone; // Stored as a string as some people add + for area code etc
		int idEntered = 0;

		try {

			System.out.println("Would you like to edit customer info? (y/n)?");
			 option = input.next();
			 
			

			switch (option) {

			case "y":
				getCustomersFromDB();

				System.out.println("Enter a UserID to edit/update: ");
				idEntered = input.nextInt();
				input.nextLine(); // Stop from skipping input

				// CHeck if the user exists in the db

				//checkIfUserExists(idEntered);

				System.out.println("Enter a new Username: ");
				newUsername = input.nextLine();

				System.out.println("Enter a new Email: ");
				newEmail = input.nextLine();

				System.out.println("Enter a new Address: ");
				newAddress = input.nextLine();

				System.out.println("Enter a new Phone: ");
				newPhone = input.nextLine();

				// Call the update controller class to update user info 

				uc.Update("Update User SET Username = " +"'"+ newUsername+"'" + ", email = " + "'"+newEmail+"'" + ", address = "
						+ "'"+newAddress+"'" + ", phone = " +"'" +newPhone+"'" + " WHERE UserID = " + "'" +idEntered+ "'");

				System.out.println("Attributes set, trying to save..");
				try {

				} catch (Exception e) {

					System.out.println("Unable to update customer info! ");
				}

				main.askForNumber();

				break;

			case "n":
				getCustomersFromDB();
				// Here we will ask if the user wants to delete a user

				System.out.println("Would you like to delete a user? (y/n)");
				option = input.next();
				//option = input.nextLine();

				switch (option) {
				case "y":

					// Delete the user from database
					System.out.println("Enter UserID of the Customer you want to delete: ");
					idEntered = input.nextInt();

					System.out.println("Are you sure?");
					input.nextLine();

					option = input.nextLine();
					
					switch(option) {
					
					case "y": 
						
						uc.Update("DELETE FROM User WHERE UserID = " + idEntered);
						
						break;
						
					case "n": 
						
						System.exit(0);
					}

				default:

					// main.askForNumber();

					break;
				}


					

			
			

				break;

			}

		} catch (Exception e) {

			System.out.println("An error Occured: " + e);
		}
	}

	public void enterStore() {

		// List all products
		
		System.out.println("\nProducts For Sale: ");

		getProductsFromDB();

		System.out.println("\nWould you like to buy something (y/n)? ");

		//input.nextLine();
		option = input.nextLine();

		switch (option) {

		case "y":

			System.out.println("Enter a productID to purchase: ");

			ProductIDEntered = input.nextInt();

			con.main("select Name, Description, Cost from Product where ProductID = " + ProductIDEntered);

		

			System.out.println("Add to Cart? (y/n)");
			option = input.next();

			switch (option) {

			case "y":
				System.out.println("What is your UserID? ");
				
				UserIDEntered = input.nextInt();

				uc.Update("INSERT into Cart (ProductID, UserID) values( " + ProductIDEntered + "," + UserIDEntered + ")");

				System.out.println("Adding to Cart");
				
				
				System.out.println("Thank you for shopping!");
				
				
				//review.insertReview(ProductIDEntered, UserIDEntered); //Call this from transactions
				
				//Create transaction - Do you want to pay now? 
				
				ct.newTransaction(UserIDEntered, ProductIDEntered);
				
				review.insertReview(ProductIDEntered, UserIDEntered);
				
				
				
				

				break;

			case "n":
				// Dont add product to cart

				System.out.println("Exiting...");
				break;
			}

			break;

		}

	}

	public void checkIfUserExists(int idEntered) {

	}

}
