package se.magnus.api.core.product;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class Product {
  private int productId;
  private String name;
  private int weight;
  private String serviceAddress;
}
