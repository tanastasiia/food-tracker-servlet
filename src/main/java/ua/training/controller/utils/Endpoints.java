package ua.training.controller.utils;

public enum Endpoints {

    APP("/api"),
    LOGIN (APP.getPath()+ "/login" ),
    REGISTRATION (APP.getPath()+ "/registration" ),
    HOME( APP.getPath() + "/home" ),
    ACCOUNT(APP.getPath()+ "/account"),
    STATISTICS(APP.getPath() + "/statistics"),
    ADMIN(APP.getPath()+ "/admin"),
    EMPTY("");

    private String path;
    Endpoints(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }

}
