package hello.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "options")
public class Option extends BaseEntity {

    /**
     * Parent attribute
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;

    //-------------------------------

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

}
