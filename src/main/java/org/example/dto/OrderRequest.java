package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class OrderRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 9161656783402278796L;
    private List<Product> products;

    public OrderRequest(List<Product> products) {
        this.products = products;
    }

    public OrderRequest(){
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "products=" + products +
                '}';
    }
}
