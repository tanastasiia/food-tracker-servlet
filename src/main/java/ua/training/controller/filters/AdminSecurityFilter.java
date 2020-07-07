package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.Routes;
import ua.training.model.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/foodtracker/admin", "/foodtracker/change_role", "/foodtracker/admin/change_food"})
public class AdminSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String role = (String) req.getSession().getAttribute("role");

        if(role == null || (!role.equals(Role.ROLE_ADMIN.name()))){
            Logger.getRootLogger().warn("Unauthorized request for admin page");
            res.sendRedirect(Routes.HOME.getPath());
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {
    }
}
