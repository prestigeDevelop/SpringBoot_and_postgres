package com.myjdbc.jdbcdata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDTO implements java.io.Serializable {

    private Integer id;
    //@NotBlank(message = "Username is mandatory")
    @Schema(description = "User's username", example = "JohnDoe123", type = "string", minLength = 3, maxLength = 50)
    private String username;
    private String password;
    //@NotBlank(message = "Email is mandatory")
    @Email
    @Schema(description = "User's email address", example = "test@gmail.com", format = "email", type = "string")
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getFullName() {
//        return firstName + " " + lastName;
//    }
//
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
}
