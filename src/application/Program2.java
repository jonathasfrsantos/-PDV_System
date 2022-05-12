package application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Invoice;
import entities.Product;
import entities.Sale;
import entities.SaleItem;

public class Program2 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Product p1 = new Product(1, "Dipirona 500 mg 10 cp", 2.99);
		Product p2 = new Product(2, "Paracetamol 500 mg 10 cp", 3.10);
		Product p3 = new Product(3, "Ibuprofeno 600 mg 6 cp", 5.98);
		Product p4 = new Product(4, "Dorflex 10 cp", 4.99);
		Product p5 = new Product(5, "Neosoro", 3.49);
		Product p6 = new Product(6, "Torsilax 4 cp", 2.87);

		// List<SaleItem> listPurcahsed = new ArrayList<>(); // não é preciso instanciar a lista de "itens comprados"
		List<Product> listProductsStock = new ArrayList<>(); // no exemplo do Nélio, os produtos são comunicados na
																// entrada do usuário, já meu caso, foi necessário
																// coloca-los em uma lista de "produtos no estoque para serem acessados a frente"
		
		listProductsStock.add(p1);
		listProductsStock.add(p2);
		listProductsStock.add(p3);
		listProductsStock.add(p4);
		listProductsStock.add(p5);
		listProductsStock.add(p6);

		int count = 0;
		int count_Id = 0;
		int enteredCode = 0;

		double totalValue = 0.0;

		Sale s1 = new Sale();
		do {
			System.out.println("Enter product code: [PRESS 999 to finish registration] ");
			enteredCode = sc.nextInt();
			LocalDateTime ldt = LocalDateTime.now(); // depois do primeiro produto registrado, tem ínicio a venda por isso instancio a data e hora

			s1.setId(count_Id);
			for (Product item : listProductsStock) {  // Para cada produto da lista de Produtos em estoque
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


		
		Invoice inv = new Invoice(s1);
		
		inv.printInvoice();

		sc.close();
	}
}
