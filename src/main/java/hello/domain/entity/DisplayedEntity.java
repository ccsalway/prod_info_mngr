package hello.domain.entity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class DisplayedEntity extends NamedEntity {

    /**
     * Whether the item is displayed to the User
     */
    @NotNull
    private boolean displayed = false;

    //--------------------------------------------

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

}
