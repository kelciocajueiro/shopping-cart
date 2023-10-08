package com.example.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

	private ShoppingCart cart;

	@BeforeEach
	public void setUp() {
		cart = new ShoppingCart();
	}

	@Test
	void testAddItem() {
		Product tv = new Product(1L, "TV");
		BigDecimal tvValue = BigDecimal.valueOf(10.0);
		int tvAmount = 3;

		// Add the first item
		cart.addItem(tv, tvValue, tvAmount);

		assertEquals(1, cart.getItems().size());
		assertEquals(tvValue.multiply(BigDecimal.valueOf(tvAmount)), cart.getTotalValue());

		// Add the same item again with a different unit value
		BigDecimal newTvValue = BigDecimal.valueOf(15.0);
		cart.addItem(tv, newTvValue, tvAmount);
		int newTvAmount = tvAmount * 2;

		assertEquals(1, cart.getItems().size());
		assertEquals(newTvValue.multiply(BigDecimal.valueOf(newTvAmount)), cart.getTotalValue());

		// Add a different item
		Product sofa = new Product(2L, "Sofa"); // Add another item
		BigDecimal sofaValue = BigDecimal.valueOf(20.0);
		int sofaAmount = 2;

		cart.addItem(sofa, sofaValue, sofaAmount);

		assertEquals(2, cart.getItems().size());

		BigDecimal expectedTotalValue = newTvValue.multiply(BigDecimal.valueOf(newTvAmount))
				.add(sofaValue.multiply(BigDecimal.valueOf(sofaAmount)));

		assertEquals(expectedTotalValue, cart.getTotalValue());
	}

	@Test
	void testRemoveItemByProduct() {
		Product tv = new Product(1L, "TV");
		cart.addItem(tv, BigDecimal.valueOf(10.0), 3);

		// Remove the item by product
		assertTrue(cart.removeItem(tv));
		assertEquals(0, cart.getItems().size());
		assertEquals(BigDecimal.ZERO, cart.getTotalValue());

		// Try to remove the same product again (should return false)
		assertFalse(cart.removeItem(tv));
	}

	@Test
	void testRemoveItemByPosition() {
		Product tv = new Product(1L, "TV");
		cart.addItem(tv, BigDecimal.valueOf(10.0), 3);

		// Remove the item by position
		assertTrue(cart.removeItem(0));
		assertEquals(0, cart.getItems().size());
		assertEquals(BigDecimal.ZERO, cart.getTotalValue());

		// Try to remove an item at an invalid position (should return false)
		assertFalse(cart.removeItem(0));
	}

	@Test
	void testGetItems() {
		cart.addItem(new Product(1L, "Xbox"), BigDecimal.valueOf(10.0), 3);
		cart.addItem(new Product(2L, "Fan"), BigDecimal.valueOf(20.0), 2);

		assertEquals(2, cart.getItems().size());
	}

}
