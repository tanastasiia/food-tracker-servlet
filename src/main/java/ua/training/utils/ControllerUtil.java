package ua.training.utils;

import ua.training.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ControllerUtil {

    private static class Holder {
        private static final ControllerUtil INSTANCE = new ControllerUtil();
    }

    public static ControllerUtil getInst(){
        return Holder.INSTANCE;
    }

    public Locale getLocale(HttpServletRequest request){
        return (Locale) request.getSession().getAttribute("lang");
    }

    public UserDto getUser(HttpServletRequest request){
        return (UserDto) request.getSession().getAttribute("user");
    }

    public long getUserId(HttpServletRequest request){
        return (long) request.getSession().getAttribute("userId");
    }

    public String getRole(HttpServletRequest request){
        return (String) request.getSession().getAttribute("role");
    }

    public boolean isMethodGet(HttpServletRequest request){
        return request.getMethod().equalsIgnoreCase("GET");
    }

    public boolean isAdmin(HttpServletRequest request){
        return (boolean) request.getSession().getAttribute("isAdmin");
    }

    public int getPage(HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        return page;
    }

    public int getOffset(int page, int pageSize) {
        return (page - 1) * pageSize;
    }

    public int countNumOfPages(int numOfRecords, int pageSize) {
            return (int) Math.ceil((double) numOfRecords / pageSize);
    }


}
