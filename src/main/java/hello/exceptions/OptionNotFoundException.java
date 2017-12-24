package hello.exceptions;

import hello.domain.entity.enities.Attribute;
import hello.domain.entity.enities.Product;

public class OptionNotFoundException extends Exception {

    private Product product;
    private Attribute attribute;

    // ------------------------------------------------------

    public Product getProduct() {
        return product;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    // ------------------------------------------------------

    public OptionNotFoundException(Product product, Attribute attribute) {
        this.product = product;
        this.attribute = attribute;
    }

}
