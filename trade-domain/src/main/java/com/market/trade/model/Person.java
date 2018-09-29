package com.market.trade.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Person extends BaseEntity {

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic
    @Size(min = 6, max=20)
    private String password;

    @Basic
    @Email
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "person")
    private Collection<Message> messages;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public enum Gender {
        MALE, FEMALE
    }
}
