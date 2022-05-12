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
		
		double totalValue = 0.0;
		int count = 0;
		int enteredCode = 0;
		double cashValue = 0.0;
		double changeMoney = 0.0;
		int numberInstallments = 0;

		

		Sale s1 = new Sale();
		do {
			System.out.println("Enter product code: [PRESS 999 to finish registration] ");
			enteredCode = sc.nextInt();
			LocalDateTime ldt = LocalDateTime.now(); // depois do primeiro produto registrado, tem ínicio a venda por isso instancio a data e hora

			s1.setId(1);
			for (Product item : listProductStock) {  // Para cada produto da lista de Produtos em estoque
				if (enteredCode == item.getCode()) {
					s1.setDate(ldt);
					totalValue += item.getPrice();
					s1.setTotal(totalValue);
					count += 1;
					s1.setQuantityProducts(count);
					SaleItem saleItem = new SaleItem(1, item.getPrice(), item); // aqui mudou em relação ao primeiro projeto, agora eu instancio o SaleItem passando o produto do loop como um dos parâmetros
					s1.addProducts(saleItem); // Depois adiciono o saleItem na lista de Sale(que é uma lista do tipo SaleItem)
					System.out.println(item.getName());
					System.out.println("Subtotal R$" + String.format("%.2f", s1.getTotal()));
				
				}

			}
		} while (enteredCode != 999);
		
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
		
		


		
		Invoice inv = new Invoice(s1);
		
		inv.printInvoice();

		sc.close();
	}
}
