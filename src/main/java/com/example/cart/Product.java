package com.example.cart;

public class Product {

  private Long code;
  private String description;

  public Product(Long code, String description) {
    this.code = code;
    this.description = description;
  }

  public Long getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Product product = (Product) o;

    return code.equals(product.code);
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }

  @Override
  public String toString() {
    return "Product{" + "code=" + code + ", description='" + description + '\'' + '}';
  }
}
