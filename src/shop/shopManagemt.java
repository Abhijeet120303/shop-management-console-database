package shop;

import java.io.IOException;
import java.util.Scanner;
import product.ProductManagement;
import product.Product;
import user.UserManagement;

public class shopManagemt {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		boolean caIKeepRunningTheProgram = true;
		System.out.println("@@@ Login Page @@@");
		System.out.println("Enter Username");
		String username = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		if (!UserManagement.login(username, password)) {
			System.out.println("Login Failed");
			return;
		}
		while (caIKeepRunningTheProgram == true) {
			System.out.println("@@@@ Welcome To Shop Managemet @@@@");
			System.out.println("1.User Management");
			System.out.println("2.Product Management");
			System.out.print("3.Quit");
			int a = sc.nextInt();
			if (a == 1) {
				UserManagement.usermanagement();

			} else if (a == 2) {
				ProductManagement.productmanagement();
			} else if (a == 3) {
				caIKeepRunningTheProgram = false;
				System.out.println("Program closed");
			}
		}
	}
}
