package edu.cit.gaane.caresync.features.authentication.dto;

public class LoginResponse {

    private String token;
    private Long id;
    private String firstName;
    private String lastName;
    private char middleInitial;
    private String email;
    private String role;

    public LoginResponse() {}

    public LoginResponse(
            String token,
            Long id,
            String firstName,
            String lastName,
            char middleInitial,
            String email,
            String role
    ) {
        this.token = token;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}