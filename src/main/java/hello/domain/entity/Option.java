package hello.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 32)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;

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

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

}