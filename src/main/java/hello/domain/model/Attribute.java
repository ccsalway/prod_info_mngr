package hello.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "attributes")
public class Attribute {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 32)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Transient
    private List<Option> options;

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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
