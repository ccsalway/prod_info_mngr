package hello.domain.entity.enities;

import hello.domain.entity.DisplayedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attributes")
public class Attribute extends DisplayedEntity {

    /**
     * Display sequence position
     */
    @NotNull
    private Integer position = -1;

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

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
