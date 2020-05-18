package ua.training.model.dto;

import ua.training.model.entity.User;

public class UserDto implements GenericDto<UserDto, User> {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private Integer height;
    private Integer weight;
    private String gender;
    private String activityLevel;
    private Integer age;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.gender = user.getGender();
        this.activityLevel = user.getActivityLevel();
        this.age = user.getAge();
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public User toEntity() {
        return new User.Builder().setFirstName(firstName).setLastName(lastName).setUsername(username).setActivityLevel(activityLevel)
                .setAge(age).setGender(gender).setHeight(height).setRole(role).setWeight(weight).setId(id).build();
    }
}
