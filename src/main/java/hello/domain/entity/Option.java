package hello.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "options")
public class Option extends BaseEntity {

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
     * Parent attribute
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;

    //-------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }
}
