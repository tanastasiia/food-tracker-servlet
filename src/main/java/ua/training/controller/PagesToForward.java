package ua.training.controller;

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

    public String getPath() {
        return path;
    }
}
