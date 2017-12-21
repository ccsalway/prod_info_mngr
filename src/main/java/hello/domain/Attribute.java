package hello.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "attributes", uniqueConstraints = {@UniqueConstraint(columnNames={"product_id", "name"})})
public class Attribute {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 32)
    private String name;

    @ManyToOne
    private Product product;

    //-------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
