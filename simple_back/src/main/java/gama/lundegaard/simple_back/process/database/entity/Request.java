package gama.lundegaard.simple_back.process.database.entity;

import javax.persistence.*;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUEST_ID_GENERATOR")
    @SequenceGenerator(name="REQUEST_ID_GENERATOR", sequenceName = "FOLDER_SEQ")
    private Long id;
    private String policyNumber;
    private String name;
    private String surname;
    private String message;
    @ManyToOne(fetch = FetchType.EAGER)
    private CRequestType type;

    public CRequestType getType() {
        return type;
    }

    public void setType(CRequestType type) {
        this.type = type;
    }



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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
