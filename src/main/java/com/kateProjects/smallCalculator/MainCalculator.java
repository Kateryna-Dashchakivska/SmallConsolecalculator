package com.kateProjects.smallCalculator;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainCalculator {	

	public static int scanValidNumber() {

		Scanner scan = new Scanner(System.in);
		int result = 0;
		boolean isNumberValid = false;

		while (isNumberValid == false) {
			if (scan.hasNextInt()) {
				result = scan.nextInt();
				if (result >= -20 && result <= 20) {
					System.out.println("\n" + result + " is correct!");
					isNumberValid = true;
				} else {
					System.out.println("\n" + result + " is not allowed number. Allowed number should be in the following range: [-20; 20]. Please try again.\n");
				}
			} else {
				System.out.println("\n" + scan.next() + " is not a number. Please try again.\n" );
			}
		}
		return result;
	}
	
	public static int scanOperation(int a, int b) {
		int res = 0;
		System.out.print("\nEnter first number: ");
		int first = scanValidNumber();
		System.out.print("\nEnter second number: ");
		int second = scanValidNumber();
		System.out.print("\nSelect operation you want to make: [*], [/], [+], [-]\n \nEnter a character in a single symbol: ");
		Scanner scan2 = new Scanner(System.in);
		String s1 = "\nYou have selected: ";
		String s2 = "\n\nYour Result is: ";
		boolean isOperationValid = false;
		
		while (isOperationValid == false) {
			if(scan2.hasNextLine()) {
				String userOperation = scan2.nextLine();
				if(userOperation.equals("+") || userOperation.equals("-") || userOperation.equals("*") || userOperation.equals("/")) {
					switch (userOperation) {
					case "*": 
						res = first * second;
						System.out.println(s1 + userOperation + s2 + res); 
						return res;
					case "/":
						if(second != 0) {
							res = first / second;
							System.out.println(s1 + userOperation + s2 + res); 
							return res;
						}
						else {
							System.out.println("\nYou are not allowed to divide by zero!"); 
							return res;
						}					 
					case "+": 
						res = first + second;
						System.out.println(s1 + userOperation + s2 + res);
						return res;
					case "-": 
						res = first - second;
						System.out.println(s1 + userOperation + s2 + res); 
						return res;
					default: 
						System.out.println("\nPlease select a valid character");
					}
				isOperationValid = true;
				} else {
					System.out.println("\n" + userOperation + " is not valid operation. Please try again.\n");
				}
			} else {
				System.out.println("\n" + scan2.next() + " is not valid. Please try again.\n" );
				scan2.close();
			}	
		}
		return res;
		}

	public static void main(String[] args) {

		System.out.println("\nPlease enter two numbers in range: [-20; 20] you want to operate with.");
		int first = 0;
		int second = 0;
		int result = scanOperation(first, second);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		Logger logger = Logger.getLogger("FileName");
		FileHandler fh;
		
		try { 
			fh = new FileHandler("C:\\Users\\Kateryna_Dashchakivs\\IdeaProjects\\SmallConsoleCalculator/Calculation.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("\n\rThe result of the calculation is: " + result
				+ "\n\rThe date of the calculation is: " + dateFormat.format(date) );
	}	
}