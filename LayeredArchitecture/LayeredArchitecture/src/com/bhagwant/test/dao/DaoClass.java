package com.bhagwant.test.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import com.bhagwant.test.bean.Person;

public class DaoClass implements DaoInterface {

	@Override
	public void storeIntoDatabase(Person person) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "1234");
			PreparedStatement stmt = con.prepareStatement("insert into person values(?,?,?,?,?)");

			stmt.setString(1, person.getName());// 1 specifies the first parameter in the query
			stmt.setString(2, person.getPassword());
			stmt.setInt(3, 0);
			stmt.setString(4, "");
			stmt.setInt(5, person.getAge());
			stmt.executeUpdate();
			con.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public boolean authenticate(Person person) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "1234");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from person where name='" + person.getName() + "'and Password='"
					+ person.getPassword() + "'");

			if (rs.next()) {
				con.close();
				return true;
			}
			con.close();
			return false;

		} catch (Exception e1) {
			System.out.println(e1);
		}
		return false;

	}

	public int showBalance(Person person) {
		int bal = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "1234");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select walletmoney from person where name='" + person.getName()
					+ "'and Password='" + person.getPassword() + "'");
			while (rs.next()) {
				bal = rs.getInt(1);
			}
			con.close();

		} catch (Exception e1) {
			System.out.println(e1);
		}
		return bal;
	}

	public void deposit(Person person, int money) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "1234");

			PreparedStatement stmt = con.prepareStatement("update person set walletmoney=walletmoney+'" + money
					+ "'where name='" + person.getName() + "'and password='" + person.getPassword() + "'");
			String trans = transaction(person) + "Deposit money value " + money + "\n";
			PreparedStatement stmt1 = con.prepareStatement("update person set transaction='" + trans + "'where name='"
					+ person.getName() + "'and password='" + person.getPassword() + "'");
			stmt.executeUpdate();
			stmt1.executeUpdate();
			con.close();

		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public boolean withdraw(Person person, int money) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "1234");
			int bal = showBalance(person);
			if (money > bal)
				return false;
			PreparedStatement stmt = con.prepareStatement("update person set walletmoney=walletmoney-'" + money
					+ "'where name='" + person.getName() + "'and password='" + person.getPassword() + "'");
			String trans = transaction(person) + "withdraw money value " + money + "\n";
			PreparedStatement stmt1 = con.prepareStatement("update person set transaction='" + trans + "'where name='"
					+ person.getName() + "'and password='" + person.getPassword() + "'");
			stmt.executeUpdate();
			stmt1.executeUpdate();
			con.close();
			return true;

		} catch (Exception e1) {
			System.out.println(e1);
		}
		return false;
	}

	public boolean fundTransfer(Person person, Person p1, int money) {
		String trans = "", trans1 = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "1234");
			int bal = showBalance(person);
			if (money > bal)
				return false;
			PreparedStatement stmt = con.prepareStatement("update person set walletmoney=walletmoney-'" + money
					+ "'where name='" + person.getName() + "'and password='" + person.getPassword() + "'");
			 trans = transaction(person) + "transfered money vaue " + money +" to "+p1.getName()+ "\n";
			PreparedStatement stmt1 = con.prepareStatement("update person set transaction='" + trans + "'where name='"
					+ person.getName() + "'and password='" + person.getPassword() + "'");
			
			stmt.executeUpdate();
			stmt1.executeUpdate();
			Statement stmtc = con.createStatement();
			ResultSet rs = stmtc.executeQuery("select transaction from person where name='" + p1.getName() + "'");
			while (rs.next()) {
				trans1 = rs.getString(1);
			}
		  stmt = con.prepareStatement("update person set walletmoney=walletmoney+'" + money
					+ "'where name='" + p1.getName() +  "'");
		  trans1+="receive money value "+money +" from "+person.getName()+"\n";
				  stmt1 = con.prepareStatement("update person set transaction='" + trans1 + "'where name='"
							+ p1.getName() +  "'");
				  stmt.executeUpdate();
					stmt1.executeUpdate();
					con.close();
					return true;

		} catch (Exception e1) {
			System.out.println(e1);
		}
		return false;

	}

	public String transaction(Person person) {
		String trans = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "1234");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select transaction from person where name='" + person.getName()
					+ "'and Password='" + person.getPassword() + "'");
			while (rs.next()) {
				trans = rs.getString(1);
			}
			con.close();

		} catch (Exception e1) {
			System.out.println(e1);
		}
		return trans;
	}

}
