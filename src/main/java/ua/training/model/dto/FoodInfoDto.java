package ua.training.model.dto;

import ua.training.model.dao.GenericDao;
import ua.training.model.entity.Food;
import ua.training.model.entity.FoodInfo;
import ua.training.model.entity.User;

public class FoodInfoDto implements GenericDto<FoodInfoDto, FoodInfo> {
    private FoodDto food;
    private UserDto user;
    private Boolean isGlobal;

    public FoodInfoDto(FoodInfo foodInfo) {
        this.food = new FoodDto(foodInfo.getFood());
        this.user = new UserDto(foodInfo.getUser());
        this.isGlobal = foodInfo.isGlobal();
    }
    public FoodInfoDto(FoodDto foodDto, UserDto userDto, Boolean isGlobal) {
        this.food = foodDto;
        this.user = userDto;
        this.isGlobal = isGlobal;
    }

    @Override
    public FoodInfo toEntity() {
        return new FoodInfo.Builder().setUser(user.toEntity()).setFood(food.toEntity()).setIsGlobal(isGlobal).build();
    }

    public FoodDto getFood() {
        return food;
    }

    public void setFood(FoodDto food) {
        this.food = food;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Boolean getGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
    }
}
