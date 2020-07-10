package com.bhagwant.test.service;

import java.util.Map;

import com.bhagwant.test.bean.Person;
import com.bhagwant.test.dao.DaoClass;

public class ServiceClass implements ServiceInterface {

	DaoClass dao = new DaoClass();

	@Override
	public boolean validateUserName(String userName) {
		if (userName.matches(userNamePattern))
			return true;
		else
			return false;
	}

	public boolean validateUserPassword(String userPassword) {
		if (userPassword.matches(userPasswordPattern))
			return true;
		return false;
	}

	public boolean authenticate(Person person) {
		return dao.authenticate(person);

	}

	public void storeIntoDatabase(Person person) {
		dao.storeIntoDatabase(person);

	}

	public int showBalance(Person person) {
		return dao.showBalance(person);
	}

	public void deposit(Person person,int money) {
		dao.deposit(person,money);
	}

	public boolean withdraw(Person person,int money) {
		return dao.withdraw(person,money);
	}

	public boolean fundTransfer(Person person,Person p1,int money) {
		return dao.fundTransfer(person,p1,money);
	}

	public String transaction(Person person) {
		return dao.transaction(person);
	}

//	public void test(){
//		
//	}

}
