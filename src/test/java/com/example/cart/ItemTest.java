package com.example.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

	private Item item;

	@BeforeEach
	public void setUp() {
		Product product = new Product(1L, "PlayStation 5");
		item = new Item(product, BigDecimal.valueOf(750), 5);
	}

	@Test
	void testGetTotalValue() {
		BigDecimal expectedTotal = BigDecimal.valueOf(750).multiply(BigDecimal.valueOf(5));
		BigDecimal actualTotal = item.getTotalValue();

		assertEquals(expectedTotal, actualTotal);
	}

}
