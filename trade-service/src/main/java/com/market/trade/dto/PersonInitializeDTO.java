package com.market.trade.dto;

import com.market.trade.model.Person;

public class PersonInitializeDTO {

    private String firstName;

    private String lastName;

    private Person.Gender gender;

    private String password;

    private String email;


    private PersonInitializeDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person.Gender getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setGender(Person.Gender gender) {
        this.gender = gender;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public static final class PersonInitializeDTOBuilder {
        private String firstName;
        private String lastName;
        private Person.Gender gender;
        private String password;
        private String email;

        private PersonInitializeDTOBuilder() {
        }

        public static PersonInitializeDTOBuilder aPersonInitializeDTO() {
            return new PersonInitializeDTOBuilder();
        }

        public PersonInitializeDTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonInitializeDTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonInitializeDTOBuilder withGender(Person.Gender gender) {
            this.gender = gender;
            return this;
        }

        public PersonInitializeDTOBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public PersonInitializeDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public PersonInitializeDTO build() {
            PersonInitializeDTO personInitializeDTO = new PersonInitializeDTO();
            personInitializeDTO.setFirstName(firstName);
            personInitializeDTO.setLastName(lastName);
            personInitializeDTO.setGender(gender);
            personInitializeDTO.setPassword(password);
            personInitializeDTO.setEmail(email);
            return personInitializeDTO;
        }
    }

    @Override
    public String toString() {
        return  "firstName: '" + firstName + '\'' +
                "\n lastName: '" + lastName + '\'' +
                "\n gender: " + gender +
                "\n password: '" + password + '\'' +
                "\n email: '" + email + '\'';
    }
}
