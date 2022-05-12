package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entities.enums.PaymentMethod;

public class Sale {

	private Integer id;
	private LocalDateTime date;
	private Double total;
	private Integer quantityProducts;
	// private String status;
	private PaymentMethod paymentMethod;
	private List<SaleItem> shoppingCart = new ArrayList<>();

	public Sale() {

	}

	public Sale(Integer id, LocalDateTime date, Double total, Integer quantityProducts, PaymentMethod paymentMethod) {
		this.id = id;
		this.date = date;
		this.total = total;
		this.quantityProducts = quantityProducts;
		this.paymentMethod = paymentMethod;
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime ldt) {
		this.date = ldt;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<SaleItem> getList() {
		return shoppingCart;
	}

	public Integer getQuantityProducts() {
		return this.quantityProducts;
	}

	public void setQuantityProducts(Integer quantity) {
		this.quantityProducts = quantity;
	}
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(PaymentMethod chosenFormOfPayment) {
		this.paymentMethod = chosenFormOfPayment;
	}
	

	public void addProducts(SaleItem item) {
		shoppingCart.add(item);
	}

	public void removeProducts(SaleItem item) {
		shoppingCart.remove(item);
	}


	
}
