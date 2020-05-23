package ua.training.controller.utils;

import ua.training.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class SessionAttr {

    private static class Holder {
        private static final SessionAttr INSTANCE = new SessionAttr();
    }

    public static  SessionAttr getInst(){
        return Holder.INSTANCE;
    }
    public Locale getLocale(HttpServletRequest request){
        return (Locale) request.getSession().getAttribute("lang");
    }

    public UserDto getUser(HttpServletRequest request){
        return (UserDto) request.getSession().getAttribute("user");
    }

}
