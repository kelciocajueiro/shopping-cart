package com.example.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartFactory {

	private Map<String, ShoppingCart> carts = new HashMap<>();

	/**
	 * Creates and returns a new shopping cart for the customer passed as a parameter.
	 * If there is already a shopping cart for the customer passed as a parameter, this cart must be returned.
	 */
	public ShoppingCart create(String clientId) {
		return carts.computeIfAbsent(clientId, key -> new ShoppingCart());
	}

  /**
   * Returns the value of the average ticket at the time of calling the method. The average ticket
   * value is the sum of the total value of all shopping cart divided by the number of shopping
   * carts. The returned value must be rounded to two decimal places: 0-4 should be rounded down and
   * 5-9 should be rounded up.
   */
  public BigDecimal getAvgTicketValue() {

    int cartSize = carts.size();

    if (cartSize == 0) {
      return BigDecimal.ZERO;
    }

    BigDecimal totalValueCart =
        carts.values().stream()
            .map(ShoppingCart::getTotalValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    return totalValueCart.divide(BigDecimal.valueOf(cartSize), 2, RoundingMode.HALF_UP);
  }

	/**
	 * Invalidates a shopping cart when the customer checks out or their session expires.
	 */
	public boolean invalidate(String clientId) {
		return carts.remove(clientId) != null;
	}

}
