package ua.training.controller.utils;

public enum Endpoints implements Paths {

    APP("/api"),
    LOGIN (APP.getPath()+ "/login" ),
    LOGOUT (APP.getPath()+ "/logout" ),
    REGISTRATION (APP.getPath()+ "/registration" ),
    HOME( /*APP.getPath() + */"/api/home" ),
    ACCOUNT(APP.getPath()+ "/account"),
    STATISTICS(APP.getPath() + "/statistics"),
    ADMIN(APP.getPath()+ "/admin"),

    ADD_MEAL( APP.getPath() + "/add_meal" ),
    ADD_FOOD( APP.getPath() + "/add_food" ),

    EMPTY("");

    private String path;
    Endpoints(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }

}
