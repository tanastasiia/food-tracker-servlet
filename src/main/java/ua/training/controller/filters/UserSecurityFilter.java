package ua.training.controller.filters;

import ua.training.controller.Routes;
import ua.training.model.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/foodtracker/home",
        "/foodtracker/account", "/foodtracker/change_account",  "/foodtracker/add_meal",
        "/foodtracker/add_food",  "/foodtracker/add_food", "/foodtracker/statistics",
        "/foodtracker/get_food_names", "/foodtracker/change_password"})
public class UserSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || (!role.equals(Role.ROLE_USER.name()) && !role.equals(Role.ROLE_ADMIN.name()))) {
            res.sendRedirect(Routes.LOGIN.getPath());
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
