package hello.domain.entity.enities;

import hello.domain.entity.DisplayedEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends DisplayedEntity {

    /**
     * Product description
     */
    @Lob
    private String description;

    /**
     * Associated attributes
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @OrderBy("name ASC")
    private Set<Attribute> attributes = new HashSet<>();

    /**
     * Variants
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @OrderBy("name ASC")
    private Set<Variant> variants = new HashSet<>();

    //-------------------------------

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<Variant> getVariants() {
        return variants;
    }

    public void setVariants(Set<Variant> variants) {
        this.variants = variants;
    }

}
