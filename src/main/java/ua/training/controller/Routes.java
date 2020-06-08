package ua.training.controller;

public enum Routes implements Paths {

    APP("/foodtracker"),

    LOGIN (APP.getPath()+ "/login" ),
    LOGOUT (APP.getPath()+ "/logout" ),
    REGISTRATION (APP.getPath()+ "/registration" ),
    HOME( APP.getPath() + "/home" ),
    ACCOUNT(APP.getPath()+ "/account"),
    STATISTICS(APP.getPath() + "/statistics"),
    ADMIN(APP.getPath()+ "/admin"),

    ADD_MEAL( APP.getPath() + "/add_meal" ),
    ADD_FOOD( APP.getPath() + "/add_food" ),
    CHANGE_ACCOUNT( APP.getPath() + "/change_account" ),
    CHANGE_USER_ROLE( APP.getPath() + "/change_role" );

    private String path;
    Routes(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }

}
