package application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.BancoDeDadosFake;
import entities.Invoice;
import entities.Product;
import entities.Sale;
import entities.SaleItem;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> listProductStock = BancoDeDadosFake.buscarProdutos();
		List<Product> purchasedProducts = new ArrayList<>();

		double totalValue = 0.0;
		int count = 0;
		int enteredCode = 0;
		double cashValue = 0.0;
		double changeMoney = 0.0;
		int numberInstallments = 0;
		int amountPurchased = 0;

		Sale s1 = new Sale();
		
		do {
			System.out.println("Enter product code: [PRESS 999 to finish registration] ");
			enteredCode = sc.nextInt();
			if(enteredCode == 999) {
				break;
			}
			LocalDateTime ldt = LocalDateTime.now(); 
			s1.setId(1);
			Product item = BancoDeDadosFake.buscarProdutoPorId(enteredCode, listProductStock);
			s1.setDate(ldt);
			totalValue += item.getPrice();
			s1.setTotal(totalValue);
			count += 1;
			s1.setQuantityProducts(count);
			SaleItem saleItem = new SaleItem(1, item.getPrice(), item); // aqui mudou em relação ao primeiro projeto,
																		// agora eu instancio o SaleItem passando o
																		// produto do loop como um dos parâmetros
			s1.addProducts(saleItem); // Depois adiciono o saleItem na lista de Sale(que é uma lista do tipo SaleItem)
			System.out.println(item.getName());
			System.out.println("Subtotal R$" + String.format("%.2f", s1.getTotal()));
			System.out.println("enteredCode => " + enteredCode);
		} while (enteredCode != 999);
	

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
