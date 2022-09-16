package gama.lundegaard.simple_back.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RequestTypeDTO {
    @NotNull(message = "The id cannot be null")
    private Long id;
    private String name;

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


}
