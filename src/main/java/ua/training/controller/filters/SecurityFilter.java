package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.RoutesToRedirect;
import ua.training.model.entity.Role;
import ua.training.utils.ControllerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    private Map<Role, List<String>> URIsMap;
    private Map<Role, RoutesToRedirect> redirectPage;

    @Override
    public void init(FilterConfig filterConfig) {

        List<String> guestURIs = Arrays.stream(RoutesToRedirect.values())
                .filter(route -> route.getAvailableFor().equals(Role.ROLE_GUEST)
                        || route.getAvailableFor().equals(Role.ROLE_ANY))
                .map(RoutesToRedirect::getPath).collect(Collectors.toList());
        List<String> userURIs = Arrays.stream(RoutesToRedirect.values())
                .filter(route -> route.getAvailableFor().equals(Role.ROLE_USER)
                        || route.getAvailableFor().equals(Role.ROLE_ANY))
                .map(RoutesToRedirect::getPath).collect(Collectors.toList());
        List<String> adminURIs = Arrays.stream(RoutesToRedirect.values())
                .filter(route -> route.getAvailableFor().equals(Role.ROLE_ADMIN) || route.getAvailableFor().equals(Role.ROLE_USER)
                        || route.getAvailableFor().equals(Role.ROLE_ANY))
                .map(RoutesToRedirect::getPath).collect(Collectors.toList());

        URIsMap = new HashMap<>();
        URIsMap.put(Role.ROLE_GUEST, guestURIs);
        URIsMap.put(Role.ROLE_USER, userURIs);
        URIsMap.put(Role.ROLE_ADMIN, adminURIs);

        redirectPage = new HashMap<>();
        redirectPage.put(Role.ROLE_GUEST, RoutesToRedirect.LOGIN);
        redirectPage.put(Role.ROLE_USER, RoutesToRedirect.HOME);
        redirectPage.put(Role.ROLE_ADMIN, RoutesToRedirect.HOME);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Role role = Role.valueOf(ControllerUtil.getInst().getRole(request));

        if (Pattern.matches("/static/(css|js)/(.+)\\.(css|js)", request.getRequestURI())
                || URIsMap.get(role).contains(request.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Logger.getRootLogger().warn("Unauthorized request to " + request.getRequestURI() + " for " + role);
            redirectPage.get(role).go(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
