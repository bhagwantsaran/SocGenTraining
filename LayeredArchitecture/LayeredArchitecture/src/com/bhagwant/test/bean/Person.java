package com.bhagwant.test.bean;

public class Person {
	private String name;
	private String password;
	private int age;
	private int walletMoney;
	private String transaction;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getPassword()
	{
		return password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getWalletMoney()
	{
		return walletMoney;
	}
	public void setWalletMoney(int money)
	{
		this.walletMoney+=money;
	}
	public String getTransaction()
	{
		return transaction;
	}
	public void setTransaction(String transaction)
	{
		this.transaction+=transaction;
	}
	
	
	
	

}
