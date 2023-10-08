package com.example.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShoppingCartFactoryTest {

	private ShoppingCartFactory factory;

	@BeforeEach
	public void setUp() {
		factory = new ShoppingCartFactory();
	}

	@Test
	void testCreateNewCart() {
		ShoppingCart cart = factory.create("customer");
		assertNotNull(cart);
	}

	@Test
	@DisplayName("Ensure that both references point to the same cart")
	void testCreateExistingCart() {
		assertSame(factory.create("customer2"), factory.create("customer2"));
	}

	@Test
	void testGetAvgTicketValue() {
		// Create multiple carts with different values
		ShoppingCart cart1 = factory.create("john");
		cart1.addItem(new Product(1L, "Xbox"), BigDecimal.valueOf(10.0), 2);

		ShoppingCart cart2 = factory.create("albert");
		cart2.addItem(new Product(2L, "TV"), BigDecimal.valueOf(20.0), 3);

		// Calculate the expected average ticket value
		BigDecimal expectedAvgTicketValue = BigDecimal.valueOf((2 * 10.0 + 3 * 20.0) / 2)
				.setScale(2, RoundingMode.HALF_UP);

		assertEquals(expectedAvgTicketValue, factory.getAvgTicketValue());
	}

	@Test
	@DisplayName("When there are no carts, the average ticket value should be zero")
	void testGetAvgTicketValueWithNoCarts() {
		assertEquals(BigDecimal.ZERO, factory.getAvgTicketValue());
	}

	@Test
	void testInvalidateCart() {
		ShoppingCart cart = factory.create("customer");

		// Invalidate the cart and ensure it is removed
		assertTrue(factory.invalidate("customer"));

		// Attempt to invalidate the same cart again (should return false)
		assertFalse(factory.invalidate("customer"));
	}

}
