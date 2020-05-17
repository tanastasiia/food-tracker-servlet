package ua.training.controller.utils;

public enum Params {
    ERROR,
    LOGOUT;

    public String getParam(){
        return this.name().toLowerCase();
    }
}
