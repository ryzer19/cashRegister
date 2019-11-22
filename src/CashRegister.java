import java.util.HashMap;								//allows user to use HashMap
import java.util.Map;									//allows user to use Map function		-- //external libraries
import java.util.Scanner;								//allows user input

public class CashRegister { 							//start of class

		public static double total;						//total will be a double
		public static double tender;					//tender will be a double value
		double userInputItemPrice = 0;					//item price will be a double
	
		static Scanner in = new Scanner(System.in);		//user input
	
		//Main Program 
		public static void main(String[] args) 
		{	 		
		    StartCashRegister();						//Cash register started
		}

		private static void StartCashRegister() {
			//Present a screen to the user with instructions on how to use the cash register
			introMessage();
			//Allow items and prices to be entered
			getItemsAndPrices();
		}
	    
		//all text between inverted comma's is displayed on screen
		private static void introMessage() {
			System.out.print("\nWelcome!" );
			System.out.print("\n\n======================================================");
			System.out.print("\n\nCash Register:\n");
			System.out.print("\n1)Enter in the item name and the price of it below.");	
			System.out.print("\n2)When you are finished entering items, ");
			System.out.print("\npress 't' to get the total cost.");	
			System.out.print("\n3)Enter the tender amount and your change");	
			System.out.print("\nwill be displayed following.");	
			System.out.print("\n\n======================================================\n");	
		}
				
		//method used to allow user to enter the item name and price of the item
		private static void getItemsAndPrices()
		{						
			Map<String, Double> itemList = new HashMap<String, Double>();	//stores item names and price in a list called itemList
			String userInputItemName = ("");								//item name will be a string
			double userInputItemPrice = 0;									//item price will be a double																					
			
			do //loop while the user hasn't totalled up the items
			{
				try												//start of try,				
				{ 
				System.out.println("\nEnter Item Name:"); 		//display message to input an item
				userInputItemName = in.next();					//set userInputItemName to input text
				
				if(userInputItemName.equals("t"))				//check if T, and if so, exit and print total
				{
					PrintOutTotal(itemList); 					//prints total
					break; 										//moves to next thing
				}
				else if(userInputItemName.equals("r"))	//check if r, and if so, restart the cash register
				{
					StartCashRegister();				
					break;  //exit loop
				}
									
				userInputItemPrice = getPriceFromUser(userInputItemPrice); // call a function to return the price
				
				itemList.put(userInputItemName, userInputItemPrice);	//add item and price to the hashmap (a table)
				
				System.out.println("____________________________\n");
				System.out.println(userInputItemName+ ": €" 
				+ Double.toString(userInputItemPrice) + " added to your list");		
				System.out.println("============================\n");
				} 																//end of try, do-while statement
				catch (Exception err) 
				{																//catch any error, e.g string price in price
					System.out.print("\nError has occured during input. Please try again \n");
					//getItemsAndPrices();
					//allow user to re-type the item price
				}
			}while(userInputItemName.toLowerCase() != "t");
			
		}

		//Get the price from user, and handle incorrect inputs
		private static Double getPriceFromUser(double userInputItemPrice) {
			System.out.println("\nEnter Item Price:");			//display price input message
			try
			{
			userInputItemPrice = in.nextDouble(); //set to price
			}
			catch(Exception ex)
			{
				System.out.println("Incorrect, please re-enter a price");
				in.next(); // have to call this or infinite loop according to google.
				userInputItemPrice = getPriceFromUser(userInputItemPrice); //try to call again
			}
			return userInputItemPrice;
		}
		
		//method used to print out the 'receipt'
		private static void PrintOutTotal(Map<String, Double> itemList) 
		{		
			Double change = 0.0;
			System.out.println("\n____________________________");  
			System.out.println("\nReceipt:\n");  
			for(Map.Entry<String, Double> entry : itemList.entrySet()) //loop through all of the items in itemList
			{	
				total = total + entry.getValue(); //update the total for each item
				System.out.println(entry.getKey() +": €" + entry.getValue()); //print out the name of each item + Price
			}			
			totalAndRequestTender();			
			change = calculateChange(change);
		}
		
		//method used to calculate and return the change amount
		private static Double calculateChange(Double change) 
		{
			System.out.print("\nEnter the tender amount.");
			tender = in.nextDouble();
			if (tender < total) 
				{
					System.out.print("\nThere is insufficient funds to pay.\n");
					//set tender back to 0
					tender = 0.0;
					//start process again 
					calculateChange(change);					
				}				
			else if (tender >= total) 	
				{	
					 change = tender - total;									//change = tender - total, e.g €5 = €10 - €5
					System.out.println("\nThe change is displayed below.");
					System.out.println("\n____________________________");
					System.out.println("\nChange:");
					System.out.println("€" + change);									//displays change amount
					System.out.println("\n============================");
					System.out.print("\n  Have a nice day! :)   ");
					System.out.print("\n____________________________\n");




				}
			return  change;	
		}
		
		//displays the total price of the items, e.g. €3 + €6 + €4 = Total: €13
		private static void totalAndRequestTender() 
		{
			System.out.print("\n____________________________\n");
			System.out.print("\nThe total is: \n");
			System.out.println("€" + total);										//displays the total of all items
			System.out.print("\n============================");
		}
}