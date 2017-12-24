package hello.domain.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attributes")
public class Attribute extends BaseEntity {

    /**
     * Parent product
     */
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: normally the product has already been fetched
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Associated options
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "attribute")
    @OrderBy("name ASC")
    private Set<Option> options = new HashSet<>();

    //-------------------------------

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

}
