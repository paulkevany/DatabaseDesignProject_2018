import java.util.Scanner;

public class Main {

	static MySQLCon con = new MySQLCon();
	static DatabaseController db = new DatabaseController();
	static storeController sc = new storeController();
	static CreateUser createUser = new CreateUser();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		askForNumber();

	}

	public static void askForNumber() {
	
		
		
		
		try {

			Scanner input = new Scanner(System.in);

			// System.out.println("Please enter a username to continue");

			System.out.println("----------Admin Interface---------- ");
			System.out.println("\n 1. View all Customers");
			System.out.println("\n 2. View all Products");
			System.out.println("\n 3. View All Carts");
			System.out.println("\n 4. Create New User");
			System.out.println("\n 5. Edit Product");
			System.out.println("\n 6. Edit Customer Info");
			System.out.println("\n 7. Enter Store");

			int option = input.nextInt();

			switch (option) {

			case 1:
				// Call getProductsFromDB in listProducts class

				db.getCustomersFromDB();
				askForNumber();

				break;

			case 2:

				// View all current products
				db.getProductsFromDB();
				askForNumber();

				break;

			case 3:
				// Display Carts

				db.getCartsFromDB();
				askForNumber();
				break;

			case 4:
				
				createUser.main();
				askForNumber();
				break;
				
				
				
			case 5:
				db.editProducts();
				askForNumber();
				break;

				
				

			case 6:
				
				db.editUserInfo();
				askForNumber();
				break;
				
			case 7: 

				
				db.enterStore();
				askForNumber();
				break;

			default:

				askForNumber();

				break;

			}

		} catch (Exception e) {
			System.out.println("An error has occured");

		}

	}
}
