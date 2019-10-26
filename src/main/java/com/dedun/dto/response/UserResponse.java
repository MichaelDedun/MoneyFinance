package com.dedun.dto.response;

public class UserResponse {
    private Integer id;
    private String login;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;


    public UserResponse() {
    }

    public UserResponse(Integer id, String login, String firstName, String lastName, String patronymic, String email) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public UserResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserResponse setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public UserResponse setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResponse setEmail(String email) {
        this.email = email;
        return this;
    }
}
