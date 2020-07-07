package ua.training.controller;

import ua.training.model.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public enum PagesToForward implements Paths {

    LOGIN("/WEB-INF/login.jsp"),
    REGISTRATION("/WEB-INF/registration.jsp"),

    HOME("/WEB-INF/user/home.jsp"),
    ACCOUNT("/WEB-INF/user/account.jsp"),
    STATISTICS("/WEB-INF/user/statistics.jsp"),

    ADMIN("/WEB-INF/admin/admin_page.jsp"),
    CHANGE_ACCOUNT("/WEB-INF/user/change_account.jsp"),
    CHANGE_PASSWORD("/WEB-INF/user/change_password.jsp"),
    CHANGE_FOOD("/WEB-INF/admin/change_food.jsp"),

    NONE(""),
    ERROR("/WEB-INF/error.jsp");

    private String path;

    PagesToForward(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void go(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
