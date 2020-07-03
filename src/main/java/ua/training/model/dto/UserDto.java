package ua.training.model.dto;

import java.time.LocalDate;
import java.util.Objects;

public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private Integer height;
    private Integer weight;
    private String gender;
    private String activityLevel;
    private LocalDate dateOfBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", gender='" + gender + '\'' +
                ", activityLevel='" + activityLevel + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(username, userDto.username) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(role, userDto.role) &&
                Objects.equals(height, userDto.height) &&
                Objects.equals(weight, userDto.weight) &&
                Objects.equals(gender, userDto.gender) &&
                Objects.equals(activityLevel, userDto.activityLevel) &&
                Objects.equals(dateOfBirth, userDto.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, role, height, weight, gender, activityLevel, dateOfBirth);
    }
}
