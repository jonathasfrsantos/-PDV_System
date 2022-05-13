package application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.BancoDeDadosFake;
import entities.Invoice;
import entities.Monitor;
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

		Monitor mon = new Monitor();
		
		mon.finalizarCompra(totalValue, s1);
		
		sc.close();
	}
}
