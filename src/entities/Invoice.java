package entities;

import java.time.format.DateTimeFormatter;

public class Invoice {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	private Sale sale;
	
	public Invoice() {
		
	}

	public Invoice(Sale sale) {
		this.sale = sale;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	public void printInvoice() {
		System.out.println("______________________________________________________");
		System.out.println(
				"\n DROGARIA LORE IPSUM \n AVENIDA LORE IPSUM, Nº 100, LOJA 3 \n CENTRO - RIO DE JANEIRO - RJ");
		System.out.println("______________________________________________________");
		System.out.println("\n Documento Auxiliar de Nota Fiscal de Consumidor Eletrônica");
		System.out.println("______________________________________________________");
		System.out.println("ITEM ----- CODE ---- DESCRIPTION ---- PRICE R$");
		System.out.println("______________________________________________________");
		int count = 0;
		for (SaleItem item : sale.getList()) { // Aqui agora eu não itero sobre uma lista do tipo Product, mas sim uma lista do tipo SaleItem
			count += 1;								
			System.out.println(
					// E assim eu consigo através da composição de objetos pegar os atributos dos produtos dentro da lista SaleItem do objeto do tipo Sale
					count + "..........." + item.getProduct().getCode()  + "......." + item.getProduct().getName() + "......." + item.getProduct().getPrice());
					// cada "item" é um tipo SaleItem, e como cada SaleItem tem um product, eu consigo acessar os products dos objetos da classe SaleItem
		}
		System.out.println("______________________________________________________");
		System.out.println("TOTAL:   R$ --------------------------------------" + String.format("%.2f", sale.getTotal()));
	
		System.out.println("______________________________________________________");
		System.out.println("DANFE Nº : xxxxxx Emissão: " + sale.getDate().format(formatter));
		
	}
	
	
	
	

}
