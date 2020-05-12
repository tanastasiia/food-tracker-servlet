package ua.training.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private String password;
    private Integer height;
    private Integer weight;
    private String gender;
    private String activityLevel;
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", gender='" + gender + '\'' +
                ", activityLevel='" + activityLevel + '\'' +
                ", age=" + age +
                '}';
    }

    public static class Builder {
        protected User user;

        public Builder() {
            user = new User();
        }

        public Builder setId(Long id) {
            user.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            user.username = username;
            return this;
        }
        public Builder setFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setRole(String role) {
            user.role = role;
            return this;
        }

        public Builder setHeight(Integer height) {
            user.height = height;
            return this;
        }
        public Builder setWeight(Integer weight) {
            user.weight = weight;
            return this;
        }

        public Builder setGender(String gender) {
            user.gender = gender;
            return this;
        }
        public Builder setActivityLevel(String activityLevel) {
            user.activityLevel = activityLevel;
            return this;
        }
        public Builder setAge(Integer age) {
            user.age = age;
            return this;
        }
        public User build() {
            return user;
        }
    }
/////////////////////////////////
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}