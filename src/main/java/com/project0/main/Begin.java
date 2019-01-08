package com.project0.main;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		
		boolean exit = false;
		
		// start application loop
		while (!exit) {
			login();
			
			application();
			
			exit = logout();
		}
		
		//System.out.println(User_Model.getCurrent().toString());
		scan.next();
		
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
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println("(view accounts/view transactions/logout)");
			String input = scan.next();
			
			if (input.equals("view accounts")) {
				
				continue;
			} else if (input.equals("view transactions")) {
				
				continue;
			} else if (input.equals("logout")) {
				break;
			} else {
				System.out.println("invalid input");
				continue;
			}
		}
		return;
	}
}
