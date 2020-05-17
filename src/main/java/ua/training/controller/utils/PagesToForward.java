package ua.training.controller.utils;

public enum PagesToForward implements Paths {

    LOGIN("/WEB-INF/login.jsp"),
    REGISTRATION("/WEB-INF/registration.jsp"),

    HOME("/WEB-INF/user/home.jsp"),
    ACCOUNT("/WEB-INF/user/account.jsp"),
    STATISTICS("/WEB-INF/user/statistics.jsp"),

    ADMIN("/WEB-INF/admin/admin.jsp"),
    NONE(""),

    HOMEHOME("/api/home"),

    ADD_FOOD("/api/add_food" );

    private String path;

    PagesToForward(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
