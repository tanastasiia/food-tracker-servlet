package ua.training.controller.command;

import ua.training.controller.utils.Constants;
import ua.training.controller.utils.Endpoints;
import ua.training.controller.utils.PagesToForward;
import ua.training.model.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        System.out.println("name: " + username + "\npass: " + pass);

        if (username == null || username.equals("") || pass == null || pass.equals("")) {
            return PagesToForward.LOGIN;
        }

        //TODO auth
        Optional<User> user = (new UserService()).findByUsername(username);

        if (user.isPresent()) {
            if(user.get().getPassword().equals(pass)){
                HttpSession session = request.getSession();
                session.setAttribute("role", "admin");

                System.out.println("logged");
                response.sendRedirect(Endpoints.HOME.getPath());
            }else {
                request.setAttribute("auth_error", true);
                System.out.println("auth_error");
                return PagesToForward.LOGIN;
            }
        }

        return PagesToForward.EMPTY;

    }
}
