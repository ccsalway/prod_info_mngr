package hello.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attributes")
public class Attribute extends BaseEntity {

    /**
     * Name as it appears on the form
     */
    @NotEmpty
    @Size(min = 1, max = 32)
    private String name;

    /**
     * Whether it is displayed in forms
     */
    @NotNull
    private boolean displayed = false;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
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
