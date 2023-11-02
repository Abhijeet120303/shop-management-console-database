package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import db_operation.DBUtils;

public class ProductManagement {

	public static void productmanagement() throws IOException {
		Scanner sc = new Scanner(System.in);
		boolean canIKeepRunningTheProgram = true;
		while (canIKeepRunningTheProgram == true) {
			System.out.println("** Welcome To Product Managemet ***");
			System.out.println("1.Add Product");
			System.out.println("2.Search Product");
			System.out.println("3.Edit Product");
			System.out.println("4.Delete Product");
			System.out.println("5.Quit Product");
			int option = sc.nextInt();
			if (option == 1) {
				addProduct();
				System.out.println("\n");
			} else if (option == 2) {
				System.out.println("Enter Name Which wnat To Search");
				sc.nextLine();
				String s = sc.next();
				serachProduct(s);
			} else if (option == 3) {
				System.out.println("Enter Name Which want To Edit");
				sc.nextLine();
				String editName = sc.next();
				editProduct(editName);
			} else if (option == 4) {
				System.out.println("Enter Name which want to Delete");
				sc.nextLine();
				String deleteProduct = sc.next();
				deleteProduct(deleteProduct);
			} else if (option == 5) {
				canIKeepRunningTheProgram = false;
				System.out.print("Program Closed");
			}
		}

	}

	public static void editProduct(String editName) {
		String Query = "select * from product where Name = '" + editName + "'";
		ResultSet rs = DBUtils.executeQueryGetResult(Query);
		try {

			if (rs.next()) {
				Scanner sc = new Scanner(System.in);

				System.out.print("New ID is :");
				String ID = sc.nextLine();

				System.out.print("New Name is :");
				String Name = sc.nextLine();

				System.out.print("New Price is :");
				String Price = sc.nextLine();

				System.out.print("New Quantity is :");
				String Quantity = sc.nextLine();

				String query = "UPDATE product SET ID = '" + ID + "', " + "Name = '" + Name + "', " + "Price = '"
						+ Price + "', " + "Quantity = '" + Quantity + "'" + "WHERE Name = '" + editName + "'";

				DBUtils.executeQuery(query);

				System.out.println("User updated successfully.");

			} else {
				System.out.println("!!!!USER NOT FOUND!!!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void serachProduct(String serachName) {
		String query = "SELECT * FROM product WHERE Name = '" + serachName + "'";

		ResultSet rs = DBUtils.executeQueryGetResult(query);

		try {
			while (rs.next()) {
				if (rs.getString("name").equalsIgnoreCase(serachName)) {
					System.out.println("ID: " + rs.getString("ID"));
					System.out.println("Name: " + rs.getString("Name"));
					System.out.println("Price: " + rs.getString("Price"));
					System.out.println("Quantity: " + rs.getString("Quantity"));
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("User Not Found");
		}

	}

	public static void addProduct() {
		Scanner sc = new Scanner(System.in);
		Product u = new Product();
		System.out.println("Enter ID :");
		u.ID = sc.nextLine();
		System.out.println("Enter Name :");
		u.name = sc.nextLine();
		System.out.println("Enter Price :");
		u.price = sc.nextLine();
		System.out.println("Enter Quantity :");
		u.quantity = sc.nextLine();

		System.out.println("ID :" + u.ID);
		System.out.println("Name :" + u.name);
		System.out.println("Price :" + u.price);
		System.out.println("Quantity :" + u.quantity);

		String query = "INSERT INTO product(ID,Name,Price,Quantity) Values ('" + u.ID + "','" + u.name + "','" + u.price
				+ "','" + u.quantity + "')";
		DBUtils.executeQuery(query);
	}

	public static void deleteProduct(String deleteProduct) {
		String query = "delete from product where name='" + deleteProduct + "' ";
		DBUtils.executeQuery(query);
	}
}
