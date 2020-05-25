package ua.training.controller.command;

import ua.training.controller.utils.ControllerUtil;
import ua.training.controller.utils.Routes;
import ua.training.controller.utils.PagesToForward;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.Role;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class LoginCommand implements Command {
    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    //private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return PagesToForward.LOGIN;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<UserDto> user = UserService.getInstance().authentication(username, password);
        if(user.isPresent()){
            HttpSession session = request.getSession();
            session.setAttribute("role", user.get().getRole());
            session.setAttribute("user", user.get());
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("isAdmin", user.get().getRole().equals(Role.ADMIN.name()));

            Logger.getLogger(LoginCommand.class.getName()).info("User logged in: " + user.get());
            response.sendRedirect(Routes.HOME.getPath());

            return PagesToForward.NONE;
        } else{
            Logger.getLogger(LoginCommand.class.getName()).info("Authentication error");
            request.setAttribute("authError", true);
            return PagesToForward.LOGIN;
        }

    }
}
