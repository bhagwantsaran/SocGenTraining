package com.bhagwant.test.service;

import java.util.Map;

import com.bhagwant.test.bean.Person;

public interface ServiceInterface {
	String userNamePattern="[A-Z][a-z]{7}";
	String userPasswordPattern="[A-Z][a-z][0-9]{8}";
	boolean validateUserName(String userName);
	boolean validateUserPassword(String userPassword);
	void storeIntoDatabase(Person person);
	boolean authenticate(Person person);
	int showBalance(Person person);
	void deposit(Person person,int money);
	boolean withdraw(Person person,int money);
	boolean fundTransfer(Person person,Person p1,int money);
	String transaction(Person person);

}
