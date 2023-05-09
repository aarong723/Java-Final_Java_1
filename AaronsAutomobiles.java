//Aaron Gold
//Final Project
import java.io.*;
import java.util.Scanner;

	public class AaronsAutomobiles { 
		final static Scanner keyboard = new Scanner(System.in); 

		public static void main(String[] args) throws IOException {
			
			int carCount = 30, year, index = 0, choice;
			String make, model, fname, lname, color;
			char option; // 'l' = lease, 'p' = purchase
			double price;
			boolean available, done=false;

			File file = new File("AaronsAutomobiles.txt");
		    Scanner inputFile = new Scanner(file);
			
			Car[] cars = new Car[carCount];
			
		    do {
				color = inputFile.next();
				year = inputFile.nextInt();
				make = inputFile.next();
				model = inputFile.next();
				option = inputFile.next().toUpperCase().charAt(0);
				price = inputFile.nextDouble();
				available = inputFile.nextBoolean();
				
			 	cars[index] = new Car(color, year, make, model, option, price, available);
			 	index ++;
			 }while (inputFile.hasNext());
			
			System.out.println("Please sign in so we may assist you...\n");
			System.out.print("First and last name: ");
			fname = keyboard.next(); lname = keyboard.next();
			System.out.println("Welcome " + fname + " " + lname + ", let's go shopping for a car!");

			while (!done)
			{
				System.out.print("\n********    Welcome to Aaron's Automobiles  ********\n**** We have the best car prices around!! ****" 
						+ "\n\nHow can we help you?\n         1. Purchase a Car\n         2. Lease a Car\n         3. Quit\n" 
						+ "\nPlease enter 1, 2, 3? ");

				choice = keyboard.nextInt();
				System.out.println();
				
				switch (choice) 
				{
					case 1: purchaseCar(cars);
							 break;
							
					case 2: leaseCar(cars);
					 		 break;
					 
					case 3: done = true;
							 break;
							
					default: System.out.println("Invalid choice, try again...");
				}
				backupInventory(cars);
			}
			System.out.println("Thank you for shopping at Aaron's Automobiles");
			System.out.println("Program Ended...");
			
		    inputFile.close();
			keyboard.close();
		}
		public static void searchInventory(Car[] cars, char opt)
		{	
			System.out.print("Stock # 						     Car Information\n\n 			    	      " 
					+ "         Color | Year |   Make  |   Model  | Price \n________ 			    " 
					+ "	      _____________________________________________\n");
			 for(int index = 0; index < cars.length; index++) //Shows all cars available
			 {
				 if(cars[index] != null && cars[index].getOption() == opt && cars[index].isAvailable() == true)
				 {
					System.out.print("   ");
					System.out.printf("%-3d", (index + 1));
					System.out.print("                                        " + (cars[index].initialToString()) + "\n"); 
				 }
			 }
		}	
		public static void purchaseCar(Car[] cars)
		{
			char purchaseResponse, purchaseConfirmation;
			int carChoice, tac;
			double carPrice = 0, initialPrice = 0;
			String thanks = "Thank you for your consideration. Please come back another time.\n";
	
			System.out.println("Here are all of the cars we have available for purchase: "); //Shows cars available for purchase
				searchInventory(cars, 'P');
				keyboard.nextLine();
			System.out.print("Would you like to purchase a car today? (Y/N): ");
				purchaseResponse = keyboard.nextLine().toUpperCase().charAt(0);
			
			if (purchaseResponse == 'Y')
			{
				System.out.print("Enter the stock number of the car you would like to purchase: ");
				carChoice = (keyboard.nextInt()-1);
				initialPrice = (cars[carChoice].getPrice());
				carPrice = (initialPrice * 1.0625);
				
				System.out.print("Great choice! You have selected a " + cars[carChoice].getColor() + " " 
				+ cars[carChoice].getYear() + " " + cars[carChoice].getMake() +  " " + cars[carChoice].getModel());
				System.out.printf(" with a subtotal price of: $%,.2f and a total price of: $%,.2f.", initialPrice, carPrice); //Tells customer inital price and price with tax
				
					keyboard.nextLine();
					
						tac = termsAndContions();
				if( tac == 1)
					{
						System.out.println("\nPlease confirm you would like to purchase this car (Y/N): ");
							purchaseConfirmation = keyboard.nextLine().toUpperCase().charAt(0);
						
						if (purchaseConfirmation == 'Y')
						{
							System.out.print("\nPlease bring this slip to the clerk window to complete your transaction. Congratulations on your purchase!\n");
							cars[carChoice].setAvailability(false); 
						}
						else
						{
							System.out.print(thanks);
						}	
					}
				else if (tac == 2)
				{
					System.out.print(thanks);
				}
			else
			{
				System.out.print(thanks);
			}	
			}
		}
		public static void leaseCar(Car[] cars)
		{
			char purchaseResponse, confirmPurchase, leaseChoice;
			int carChoice, months = 0, tac;
			double downPayment, monthlyFee= 0;
			String thanks = "Thank you for your consideration. Please come back another time.\n";
			
					System.out.println("Here are all of the cars we have available to lease: "); //Shows available cars to lease
					searchInventory(cars, 'L');
					keyboard.nextLine();
					
					System.out.print("Would you like to lease a car today? (Y/N): ");
					purchaseResponse = keyboard.nextLine().toUpperCase().charAt(0);
					
					if (purchaseResponse == 'Y')
					{
						System.out.print("Enter the stock number of the car you would like to lease: ");
						carChoice = (keyboard.nextInt()-1);
						
						System.out.print("Great choice! You have selected a " + cars[carChoice].getColor() + " " 
						+ cars[carChoice].getYear() + " " + cars[carChoice].getMake() + " " +  cars[carChoice].getModel());
						
						downPayment= cars[carChoice].getPrice() * 0.15;  //Package A
						monthlyFee = ((cars[carChoice].getPrice() * 0.5) - downPayment) / 24.0;
						System.out.printf("\n\nHere are our available packages: \nPackage A: Down payment of only $%,.2f and a low monthly fee of $%,.2f for just 24 months!", downPayment, monthlyFee); 
					
						downPayment= cars[carChoice].getPrice() * 0.13;  
						monthlyFee = ((cars[carChoice].getPrice() * 0.5) - downPayment) / 36.0; //Package B
						System.out.printf("\nPackage B: Down payment of only $%,.2f and a low monthly fee of $%,.2f for just 36 months!", downPayment, monthlyFee);
						
						downPayment= cars[carChoice].getPrice() * 0.10;  
						monthlyFee = ((cars[carChoice].getPrice() * 0.5) - downPayment) / 48.0; //Package C
						System.out.printf("\nPackage C: Down payment of only $%,.2f and a low monthly fee of $%,.2f for just 48 months!" , downPayment, monthlyFee);
						
						keyboard.nextLine();
						System.out.println("\n\nWhich lease package would you like? (A/B/C): ");
						leaseChoice = keyboard.nextLine().toUpperCase().charAt(0);
						
						if (leaseChoice == 'A')
						{
							downPayment= cars[carChoice].getPrice() * 0.15;  //Package A Calculation
							monthlyFee = ((cars[carChoice].getPrice() * 0.5) - downPayment) / 24.0;
							months = 24;
						}
						else if (leaseChoice == 'B')
						{
							downPayment= cars[carChoice].getPrice() * 0.13;  //Package B Calculation
							monthlyFee = ((cars[carChoice].getPrice() * 0.5) - downPayment) / 36.0;
							months = 36;
						}
						else if (leaseChoice == 'C')
						{
							downPayment= cars[carChoice].getPrice() * 0.10;  //Package C Calculation
							monthlyFee = ((cars[carChoice].getPrice() * 0.5) - downPayment) / 48.0;
							months = 48;
						}
						
						System.out.printf("Your total down payment is: $%,.2f. \nYour monthly payment is: $%,.2f for %d months.", downPayment, monthlyFee, months);
						
						tac = termsAndContions(); //Has customer check terms and conditions
						if( tac == 1)
						{
							System.out.print("\nPlease confirm you would like to lease this car (Y/N): "); //Makes sure customer would like to lease the car
							confirmPurchase = keyboard.nextLine().toUpperCase().charAt(0);
							
							if (confirmPurchase == 'Y')
							{
							System.out.print("\nPlease bring this slip to the clerk window to complete your transaction. Congratulations on your lease!\n\n");
							cars[carChoice].setAvailability(false);//Updates CarlosCars.txt to make this car unavailable
							}
							else
							{
								System.out.print(thanks);
							}
						}	
						else if (tac == 2)
						{
							System.out.print(thanks);
						}
					}
					else
					{
						System.out.print(thanks);
					}
		}
		public static void backupInventory(Car[] cars) throws IOException
		{
			String filename;	
		
				filename = "AaronsAutomobiles.txt";
				PrintWriter outputFile = new PrintWriter(filename); //Updating CarlosCars.txt with new data
			
				for(int index = 0; index < cars.length; index++)
				{
					if(cars[index].isAvailable()==true);
					{
						outputFile.println(cars[index].toString());
					}
				}
			      outputFile.close();
		}
		public static int termsAndContions()
		{
			char answer, agree;
			
			System.out.print("\nWould you like to see the terms and conditions? (Y/N): ");
				answer = keyboard.nextLine().toUpperCase().charAt(0);
			
			if (answer == 'Y')
			{
				System.out.println("\nBy purchasing or leasing this car you agree to our no-refund policy. " 
						+ "\nSlandering the name of Aaron's Automobiles will result in a severe fine and possibly jail time.");
			}
			else
			{
				System.out.print("You have chosen to skip reading the terms and conditions.\n");
			}
			System.out.print("Do you agree to the terms and conditions? (Y/N): ");
				agree = keyboard.nextLine().toUpperCase().charAt(0);
				
			if (agree == 'Y')
			{
				System.out.print("Terms and conditions agreed to.");
				return (1);
			}
			else
			{
				System.out.print("Terms and conditions denied.");
				return (2);
			}
		}
}