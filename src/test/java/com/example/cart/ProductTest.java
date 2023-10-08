package com.example.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

  private Product ps5;
  private Product ps4;
  private Product xbox;

  @BeforeEach
  public void setUp() {
    ps5 = new Product(1L, "PlayStation 5");
    ps4 = new Product(1L, "PlayStation 4");
    xbox = new Product(2L, "Xbox");
  }

  @Test
  void testProductsAreSame() {
    assertEquals(ps5, ps4);
  }

  @Test
  void testProductsAreDifferent() {
    assertNotEquals(ps5, xbox);
  }
}
