package hello.domain.entity.enities;

import hello.domain.entity.DisplayedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "options")
public class Option extends DisplayedEntity {

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

}
