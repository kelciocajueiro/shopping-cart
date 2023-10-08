package com.example.cart;

import java.math.BigDecimal;

public class Item {

	private Product product;
	private BigDecimal value;
	private int amount;

	public Item(Product product, BigDecimal value, int amount) {
		this.product = product;
		this.value = value;
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BigDecimal getTotalValue() {
		return value.multiply(BigDecimal.valueOf(amount));
	}

	@Override
	public String toString() {
		return "Item{" +
				"product=" + product +
				", value=" + value +
				", amount=" + amount +
				'}';
	}

}
