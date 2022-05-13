package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Product p1 = new Product(1, "Dipirona 500 mg 10 cp", 2.99);
		Product p2 = new Product(2, "Paracetamol 500 mg 10 cp", 3.10);
		Product p3 = new Product(3, "Ibuprofeno 600 mg 6 cp", 5.98);
		Product p4 = new Product(4, "Dorflex 10 cp", 4.99);
		Product p5 = new Product(5, "Neosoro", 3.49);
		Product p6 = new Product(6, "Torsilax 4 cp", 2.87);
		

		List<Product> listProducts = new ArrayList<>();
		List<Product> purchasedProducts = new ArrayList<>();
		int count = 0;
		int numberInstallments = 0;
		int amountPurchased = 0;

		double cashValue = 0.0;
		double changeMoney = 0.0;
		double totalValue = 0.0;

		char userAnswer = ' ';

		listProducts.add(p1);
		listProducts.add(p2);
		listProducts.add(p3);
		listProducts.add(p4);
		listProducts.add(p5);
		listProducts.add(p6);

		// First stage - question about CPF
		System.out.println("-----------------");
		System.out.println("DOES THE CUSTOMER HAVE THE CPF REGISTERED ? [1 - YES] [0 - NO]");
		int registeredCPF = sc.nextInt();
		if (registeredCPF == 0) {
			System.out.println("WOULD YOU LIKE TO REGISTER? [1 - YES] [0 - NO] ");
			int confirmationNewRegister = sc.nextInt();
			if (confirmationNewRegister == 1) {
				System.out.println("ENTER CUSTOMER NAME : ");
				String newRegisterName = sc.next();
				System.out.println("ENTER CUSTOMER CPF: ");
				String newRegisterCPF = sc.next();
				System.out.println("ENTER CUSTOMER CELL NUMBER : ");
				String newRegisterCell = sc.next();
			}
		} else if (registeredCPF == 1) {
			System.out.println("ENTER CUSTOMER CPF: ");
			String alreadyRegisteredCPF = sc.next();
		} else {
			System.out.println("INVALID OPTION! ");
		}

		// second stage -- registering products
		do {
			System.out.println("Enter product code:  ");
			Integer enteredCode = sc.nextInt();
			for (Product item : listProducts) {
				if (enteredCode == item.getCode()) {
					System.out.println("PRODUCT : " + item.getName().toUpperCase() + " ... R$ " + item.getPrice());
					totalValue += item.getPrice();
					amountPurchased += 1;
					purchasedProducts.add(item);
					System.out.println("DO YOU WISH CONTINUE? -  [YES - Y] [NO -N]");
					userAnswer = sc.next().charAt(0);
				}

			}

		} while (userAnswer != 'n');

		System.out.println("_____________________________________ \n");
		System.out.printf("Subtotal %.2f %n", totalValue);
		System.out.println("_____________________________________");

		// third stage - payment methods
		System.out.println();
		System.out.println("SELECT PAYMENT METHOD : \n  1 - IN CASH \n  2 - DEBIT CARD \n 3 - CREDIT CARD \n");
		int chosenPaymentMethod = sc.nextInt();
		do {
			if (chosenPaymentMethod == 1) {
				System.out.println("ENTER CASH RECEIVED: ");
				cashValue = sc.nextDouble();
				changeMoney = cashValue - totalValue;
				System.out.printf("change %.2f", changeMoney);

			} else if (chosenPaymentMethod == 3) {
				System.out.println("How many installments? ");
				numberInstallments = sc.nextInt();
			} 

		} while (chosenPaymentMethod < 1 || chosenPaymentMethod > 3);

		// fourth stage - priting invoice and details of purchase
		System.out.println("\n ----------------------------");
		System.out.println(
				"\n DROGARIA LORE IPSUM \n AVENIDA LORE IPSUM, Nº 100, LOJA 3 \n CENTRO - RIO DE JANEIRO - RJ");
		System.out.println("\n INVOICE  ");
		System.out.println("_____________________________________");
		System.out.println("ITEM ----- CODE ---- DESCRIPTION ---- PRICE R$");
		for (Product itens : purchasedProducts) {
			count += 1;
			System.out.println(count + "----" + itens.getCode() + "----" + itens.getName() + "----" + itens.getPrice());
		}

		// fifth stage - total of bought

		System.out.println("____________________________________________________");
		System.out.println("TOTAL PRODUCTS PURCHASED: " + amountPurchased + " ITENS");
		if (chosenPaymentMethod == 1) {
			System.out.printf("MONEY RECEIVED ............... R$ %.2f %n", cashValue);
			System.out.printf("CHANGE MONEY   ............... R$ %.2f %n", changeMoney);
		} else if (chosenPaymentMethod == 3) {
			if (numberInstallments <= 1) {
				System.out.printf("CREDIT CARD ............... R$ %.2f", totalValue);
			} else if (numberInstallments > 1) {
				Double installmentAmount = totalValue / numberInstallments;
				System.out.printf("CREDIT CARD ............... IN %d X INSTALLMENTS OF %.2f %n", numberInstallments,
						installmentAmount);
			}
		} else if (chosenPaymentMethod == 2) {
			System.out.printf("DEBIT CARD ............... R$ %.2f %n", totalValue);

		}
		System.out.println(" ____________________________________________________");
		System.out.printf("PURCHASED TOTAL ............... R$ %.2f %n", totalValue);

		sc.close();

	}
}
