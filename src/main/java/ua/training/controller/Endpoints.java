package ua.training.controller;

import ua.training.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public enum Endpoints implements Paths {

    APP("/foodtracker", Role.ROLE_ANY),

    LOGIN(APP.getPath() + "/login", Role.ROLE_GUEST),
    REGISTRATION(APP.getPath() + "/registration", Role.ROLE_GUEST),
    LOGIN_LOGOUT(LOGIN.getPath() + "?logout=true", Role.ROLE_GUEST),

    LOGOUT(APP.getPath() + "/logout", Role.ROLE_USER),
    HOME(APP.getPath() + "/home", Role.ROLE_USER),
    ACCOUNT(APP.getPath() + "/account", Role.ROLE_USER),
    STATISTICS(APP.getPath() + "/statistics", Role.ROLE_USER),
    ADD_MEAL(APP.getPath() + "/add_meal", Role.ROLE_USER),
    ADD_FOOD(APP.getPath() + "/add_food", Role.ROLE_USER),
    CHANGE_ACCOUNT(APP.getPath() + "/change_account", Role.ROLE_USER),
    CHANGE_USER_PASSWORD(APP.getPath() + "/change_password", Role.ROLE_USER),
    CHANGE_FOOD(APP.getPath() + "/admin/change_food", Role.ROLE_USER),
    GET_FOOD_NAMES(APP.getPath() + "/get_food_names", Role.ROLE_USER),

    ADMIN(APP.getPath() + "/admin", Role.ROLE_ADMIN),
    CHANGE_USER_ROLE(APP.getPath() + "/change_role", Role.ROLE_ADMIN);

    private String path;
    private Role availableFor;

    Endpoints(String path, Role availableFor) {
        this.path = path;
        this.availableFor = availableFor;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void go(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(path);
    }

    public Role getAvailableFor() {
        return availableFor;
    }
}
