package gama.lundegaard.simple_back.web.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RequestDTO {
    @NotBlank(message = "The policy number is required.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The Policy number is in the wrong format")
    private String policyNumber;
    @NotBlank(message = "The name is required.")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The Name must contain only letters")
    private String name;
    @NotBlank(message = "The surname is required.")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The Surname must contain only letters")
    private String surname;
    @Size(min = 1, max = 500, message = "The message must be between 1 and 500 characters")
    private String message;
    @NotNull(message = "The request type is required")
    @Valid
    private RequestTypeDTO type;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestTypeDTO getType() {
        return type;
    }

    public void setType(RequestTypeDTO type) {
        this.type = type;
    }
}
