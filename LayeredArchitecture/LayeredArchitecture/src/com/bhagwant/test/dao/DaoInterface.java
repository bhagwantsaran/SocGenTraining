package com.bhagwant.test.dao;

import java.util.HashMap;
import java.util.Map;

import com.bhagwant.test.bean.Person;

public interface DaoInterface {
	
	
	void storeIntoDatabase(Person person);
	boolean authenticate(Person person);
	int showBalance(Person person);
	void deposit(Person person,int money);
	boolean withdraw(Person person,int money);
	boolean fundTransfer(Person person,Person p1,int money);
	String transaction(Person person);
	
}
