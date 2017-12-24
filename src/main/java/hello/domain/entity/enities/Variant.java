package hello.domain.entity.enities;

import hello.domain.entity.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Variant extends NamedEntity {

    /**
     * Parent product
     */
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: normally the product has already been fetched
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


}
