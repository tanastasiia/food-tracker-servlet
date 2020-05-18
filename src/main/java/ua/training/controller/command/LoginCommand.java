package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.utils.Endpoints;
import ua.training.controller.utils.PagesToForward;
import ua.training.model.dto.UserDto;
import ua.training.model.dto.UserAuthDto;
import ua.training.model.entity.Role;
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

        Optional<UserDto> user = UserService.getInstance().authentication(new UserAuthDto(username, password));
        if(user.isPresent()){
            HttpSession session = request.getSession();
            session.setAttribute("role", user.get().getRole());
            session.setAttribute("user", user.get());
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("isAdmin", user.get().getRole().equals(Role.ADMIN.name()));

            response.sendRedirect(Endpoints.HOME.getPath());
            return PagesToForward.NONE;
        } else{
            request.setAttribute("authError", true);
            return PagesToForward.LOGIN;
        }

    }
}
