package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.utils.Endpoints;
import ua.training.controller.utils.PagesToForward;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return PagesToForward.LOGIN;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //LOGGER.info("name: " + username + "\npass: " + password);
        System.out.println("username: " + username + "\npassword: " + password);

        Optional<User> user = UserService.getInstance().findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {

            HttpSession session = request.getSession();
            session.setAttribute("role", user.get().getRole());
            session.setAttribute("user", user.get());
            session.setAttribute("isAdmin", user.get().getRole().equals(Role.ADMIN.name()));

            System.out.println("redirecting to HOME");
            System.out.println("HOME endpoint: "+ request.getContextPath()+Endpoints.HOME.getPath());
            response.sendRedirect(/*request.getContextPath()+*/Endpoints.HOME.getPath());
            System.out.println("redirected");
            return PagesToForward.NONE;

        } else {
            request.setAttribute("auth_error", true);
            return PagesToForward.LOGIN;
        }


    }
}
