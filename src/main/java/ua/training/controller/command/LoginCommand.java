package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.controller.RoutesToRedirect;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {
    private Logger logger = LogManager.getLogger(LoginCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return PagesToForward.LOGIN;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> user = UserService.getInstance().authentication(username, password);

        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("role", user.get().getRole());
            session.setAttribute("user", user.get());
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("isAdmin", user.get().getRole().equals(Role.ROLE_ADMIN.name()));

            logger.info("User logged in: " + user.get());
            return RoutesToRedirect.HOME;
        } else {
            logger.info("Authentication error");
            request.setAttribute("authError", true);
            return PagesToForward.LOGIN;
        }

    }
}
