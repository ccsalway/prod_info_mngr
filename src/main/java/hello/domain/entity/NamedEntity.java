package hello.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public class NamedEntity extends BaseEntity {

    /**
     * Name as it appears to the user
     */
    @NotEmpty
    @Size(min = 1, max = 32)
    private String name;

    //--------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}