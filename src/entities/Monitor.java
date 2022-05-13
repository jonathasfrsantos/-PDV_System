package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Monitor {

	public static void escolherMetodoPagamento(double totalValue, List<Product> purchasedProducts) {

		System.out.println("SELECT PAYMENT METHOD : \n  1 - IN CASH \n  2 - DEBIT CARD \n 3 - CREDIT CARD \n");
		Scanner sc = new Scanner(System.in);
		double cashValue = 0.0;
		double changeMoney = 0.0;
		int numberInstallments = 0;
		int count = 0;
		int amountPurchased = 0;
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

	}

}
