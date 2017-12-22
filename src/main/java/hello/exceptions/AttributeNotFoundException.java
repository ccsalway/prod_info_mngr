package hello.exceptions;

import hello.domain.model.Product;

public class AttributeNotFoundException extends Exception {

    private Product product;

    // ------------------------------------------------------

    public Product getProduct() {
        return product;
    }

    // ------------------------------------------------------

    public AttributeNotFoundException(Product product) {
        this.product = product;
    }

}