package entities;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosFake {

	public static List<Product> buscarProdutos() {
		Product p1 = new Product(1, "Dipirona 500 mg 10 cp", 2.99);
		Product p2 = new Product(2, "Paracetamol 500 mg 10 cp", 3.10);
		Product p3 = new Product(3, "Ibuprofeno 600 mg 6 cp", 5.98);
		Product p4 = new Product(4, "Dorflex 10 cp", 4.99);
		Product p5 = new Product(5, "Neosoro", 3.49);
		Product p6 = new Product(6, "Torsilax 4 cp", 2.87);

		List<Product> listProductsStock = new ArrayList<>();

		listProductsStock.add(p1);
		listProductsStock.add(p2);
		listProductsStock.add(p3);
		listProductsStock.add(p4);
		listProductsStock.add(p5);
		listProductsStock.add(p6);
		return listProductsStock;
	}

	public static Product buscarProdutoPorId(Integer id, List<Product> listProductStock) {
		for (Product item : listProductStock) {
			if (id == item.getCode()) {
				return item;
			}
		}

		return null;

	}

}
