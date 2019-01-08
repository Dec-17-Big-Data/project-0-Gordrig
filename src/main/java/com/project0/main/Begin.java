package com.project0.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project0.models.Bank_Account_Model;
import com.project0.models.User_Model;
import com.project0.services.BankAccountService;
import com.project0.services.TransactionService;
import com.project0.services.UserService;

public class Begin {
	
	private static final Logger log = LogManager.getLogger(Begin.class);
	private static UserService us = UserService.getService();
	private static BankAccountService bas = BankAccountService.getService();
	private static TransactionService ts = TransactionService.getService();
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		log.traceEntry();
		
		scan.useDelimiter(System.lineSeparator());
		
		boolean exit = false;
		
		// start application loop
		while (!exit) {
			login();
			
			application();
			
			exit = logout();
		}
		
		log.traceExit();
	}
	
	private static void login() {
		boolean reg = false;
		System.out.println("Welcome to Banking Application!");
		System.out.println("Are you a new User? (y/n)");
		if (scan.next().equals("y")) {
			reg = true;
		}
		
		System.out.println("Please enter Username");
		String username = scan.next();
		System.out.println("Please enter Password");
		String password = scan.next();
		User_Model.setCurrent(new User_Model(null,username,password,null,null));
		if (reg) {
			us.createUser(username, password);
		}
		User_Model um = us.getUsers().get().get(0);
		User_Model.setCurrent(um);
		System.out.println("Login successful");
		return;
	}
	
	private static boolean logout() {
		System.out.println("You have successfully logged out");
		System.out.println("do you wish to exit the application? (y/n)");
		if (scan.next().equals("y")) {
			return true;
		}
		return false;
	}
	
	private static void application() {
		log.traceEntry();
		while(true) {

			System.out.println("MAIN MENU");
			System.out.println("--------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("(view accounts/view transactions/logout)");
			String input = scan.next();
			
			if (input.equals("view accounts")) {
				accounts();
				continue;
			} else if (input.equals("view transactions")) {
				transactions();
				continue;
			} else if (input.equals("logout")) {
				break;
			} else {
				System.out.println("invalid input");
				continue;
			}
		}
		log.traceExit();
		return;
	}
	
	private static void accounts() {
		log.traceEntry();
		while(true){
			List<Bank_Account_Model> lbam = bas.getAccounts().get();
			String options = "";
			options += "(";
			int i = 0;
			List<Integer> ilist = new ArrayList<Integer>();
			boolean found = false;
			for (Bank_Account_Model bam : lbam) {
				i++;
				options += "account ";
				options += i;
				options += "/";
			}
			
			options += "create account/";
			options += "back)";
			
			System.out.println("ACCOUNTS MENU");
			System.out.println("--------------------------------");
			System.out.println("What would you like to do?");
			System.out.println(options);
			
			String input = scan.next();
			
			for (Integer j: ilist) {
				if (input.equals("Account " + j)) {
					manageAccount(lbam.get(j));
					found = true;
				}
			}
			
			if (input.equals("create account")) {
				createAccount();
				continue;
			} else if (input.equals("back")) {
				break;
			}
			
			if (!found) {
				System.out.println("Invalid input");
			}
		}
		log.traceExit();
		return;
	}
	
	private static void manageAccount(Bank_Account_Model bam) {
		log.traceEntry();
		while(true) {
			System.out.println("ACCOUNT MANAGEMENT");
			System.out.println("--------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("(back)");
			String input = scan.next();
			
			if (input.equals("back")) {
				break;
			} else {
				System.out.println("Invalid input");
			}
			
		}
		log.traceExit();
		return;
	}
	
	private static void transactions() {
		log.traceEntry();
		while(true) {
			System.out.println("ALL TRANSACTIONS");
			System.out.println("--------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("(back)");
			String input = scan.next();
			
			if (input.equals("back")) {
				break;
			} else {
				System.out.println("Invalid input");
			}
		}
		log.traceExit();
		return;
	}
	
	private static void createAccount() {
		log.traceEntry();
		while(true) {
			System.out.println("ACCOUNT CREATION");
			System.out.println("--------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("(back)");
			String input = scan.next();
			
			if (input.equals("back")) {
				break;
			} else {
				System.out.println("Invalid input");
			}
		}
		log.traceExit();
		return;
	}
	
}
