package hello.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    /**
     * Name as it appears on the page and in menus
     */
    @NotEmpty
    @Size(min = 1, max = 32)
    private String name;

    /**
     * Product description
     */
    @Lob
    private String description;

    /**
     * Whether it is displayed in menus
     */
    @NotNull
    private boolean displayed = false;

    /**
     * Associated attributes
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @OrderBy("name ASC")
    private Set<Attribute> attributes = new HashSet<>();

    //-------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

}
