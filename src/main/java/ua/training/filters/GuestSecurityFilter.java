package ua.training.filters;

import ua.training.controller.utils.Endpoints;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/api/login", "/api/registration", "/api"})
public class GuestSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        System.out.println("auth filter");

        if(role!=null){
            res.sendRedirect(Endpoints.HOME.getPath());
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {
    }
}