package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
//    private Collection<Product> products = new ArrayList<>();

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        this.addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity cannot be lower than 1");
        }
        this.products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()){
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            BigDecimal priceOfThisItem = product.getPrice().multiply(quantityAsBigDecimal);
            sum = sum.add(priceOfThisItem);
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()){
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            BigDecimal priceOfTaxes = product.getTaxPrice().multiply(quantityAsBigDecimal);
            sum = sum.add(priceOfTaxes);
        }
        return sum;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()){
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            BigDecimal priceOfThisItem = product.getPriceWithTax().multiply(quantityAsBigDecimal);
            sum = sum.add(priceOfThisItem);
        }
        return sum;
    }
}
