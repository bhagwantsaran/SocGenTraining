package com.bhagwant.test.ui;

import java.util.Scanner;
import java.sql.*;

import com.bhagwant.test.bean.Person;
import com.bhagwant.test.dao.DaoInterface;
import com.bhagwant.test.service.ServiceClass;
import com.bhagwant.test.service.ServiceInterface;
import com.mysql.cj.jdbc.Driver;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		ServiceInterface service = new ServiceClass();

		// service.test();//error
		String name = "", password = "";
		int choice;
		System.out.println("choose the option:");
		System.out.println("1:login account");
		System.out.println("2:signup account");
		choice = scan.nextInt();
		do {
			if (choice == 2) {
				while (true) {

					System.out.println("enter name first letter should be capital and rest 7 letter small");
					name = scan.next();

					boolean isValid = service.validateUserName(name);

					if (isValid)
						break;
				}
				while (true) {
					System.out.println("enter password first letter capital second small and rest 8 should digit");
					password = scan.next();
					boolean isValid = service.validateUserPassword(password);

					if (isValid)
						break;
				}
				System.out.println("enter age ");
				String age = scan.next();
				Person person = new Person();
				person.setAge(Integer.parseInt(age));
				person.setName(name);
				person.setPassword(password);

				service.storeIntoDatabase(person);

			} else if (choice == 1) {

				System.out.println("enter name");
				name = scan.next();
				System.out.println("enter password");
				password = scan.next();
				Person person = new Person();
				person.setName(name);
				person.setPassword(password);
				if (service.authenticate(person)) {
					System.out.println("you are logged in \n");
					while (true) {
						System.out.println("select option for operation");
						System.out.println("1.Show Balance");
						System.out.println("2.Deposit");
						System.out.println("3.Withdraw");
						System.out.println("4.Fund Transfer");
						System.out.println("5.Print Transaction");
						System.out.println("6.exit");

						int choice1 = scan.nextInt();
						if (choice1 == 1) {
							System.out.println("Wallet balance :" + service.showBalance(person));
						} else if (choice1 == 2) {
							System.out.println("enter the money you want to deposit");
							int money=scan.nextInt();
							service.deposit(person,money);

						} else if (choice1 == 3) {
							System.out.println("enter the money you want to withdraw");
							int money=scan.nextInt();
							if(service.withdraw(person,money))
								System.out.println("Withdrawal successful");
							else
								System.out.println("you have not sufficient balance");
							
						} else if (choice1 == 4) {
							String name1="";
							int money;
							Person p1=new Person();
							System.out.println("Enter the name of customer whom you are going trasfer money");
							name1=scan.next();
							System.out.println("enter the amount you want to transfer");
							money=scan.nextInt();
							p1.setName(name1);
							
							if(service.fundTransfer(person,p1,money))
								System.out.println("Succesful transfered");
							else
								System.out.println("you have not sufficient balance");
							
						} else if (choice1 == 5) {
							
							System.out.println(service.transaction(person));
						}
						else if(choice1==6)
							break;
					}
				}

			} else {
				System.out.println("wrong choice try again");

			}
		} while (choice != 1 && choice != 2);

		// System.out.println(service.displayPersons());
	}

}
