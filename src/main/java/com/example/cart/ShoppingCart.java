package com.example.cart;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {

	private List<Item> items = new ArrayList<>();

  /**
   * Add a new item to the shopping cart. If the item already exists in the cart for this same
   * product: - The amount of the item must be the sum of the current amount and the amount passed
   * as a parameter. - If the entered unit value is different from the current unit value of the
   * item, the new unit value of the item must be the one passed as a parameter.
   */
  public void addItem(Product product, BigDecimal unitValue, int amount) {

    validateParams(product, unitValue, amount);

    Optional<Item> existingItem =
        items.stream().filter(item -> item.getProduct().equals(product)).findFirst();

    if (existingItem.isPresent()) {
      Item item = existingItem.get();
      item.setAmount(item.getAmount() + amount);

      if (unitValue.compareTo(item.getValue()) != 0) {
        item.setValue(unitValue);
      }
    } else {
      items.add(new Item(product, unitValue, amount));
    }
  }

  private void validateParams(Product product, BigDecimal unitValue, int amount) {
    if (product == null || unitValue.compareTo(BigDecimal.ZERO) <= 0 || amount <= 0) {
      throw new IllegalArgumentException("Not possible to add the Item in the Shopping Cart");
    }
  }

  /** Allows you to remove the item that represents this product from the shopping cart. */
  public boolean removeItem(Product product) {
    return items.removeIf(item -> item.getProduct().equals(product));
  }

  /**
   * Allows removal of the item according to position. This position must be determined by the order
   * in which the product is included in the collection, where zero represents the first item.
   */
  public boolean removeItem(int itemPosition) {

    if (itemPosition >= 0 && itemPosition < items.size()) {
      items.remove(itemPosition);
      return true;
    }

    return false;
  }

  /**
   * Returns the total value of the shopping cart, which must be the sum of the total values of all
   * items that make up the cart.
   */
  public BigDecimal getTotalValue() {
    return items.stream().map(Item::getTotalValue).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

	public Collection<Item> getItems() {
		return new ArrayList<>(items);
	}

}
