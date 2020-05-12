package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.utils.Endpoints;
import ua.training.controller.utils.PagesToForward;
import ua.training.model.constants.UserConst;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return PagesToForward.REGISTRATION;
        }

        User user = parseForm(request);

        LOGGER.info("User: " + user.toString());

        request.setAttribute("success", UserService.getInstance().register(user));

        return PagesToForward.REGISTRATION;
}

    private User parseForm(HttpServletRequest request) {
        return new User.Builder()
                .setUsername(request.getParameter(UserConst.USERNAME))
                .setFirstName(request.getParameter(UserConst.FIRST_NAME))
                .setLastName(request.getParameter(UserConst.LAST_NAME))
                .setRole(Role.USER.name())
                .setPassword(request.getParameter(UserConst.PASSWORD))
                .setHeight(Integer.parseInt(request.getParameter(UserConst.HEIGHT)))
                .setWeight(Integer.parseInt(request.getParameter(UserConst.WEIGHT)))
                .setGender(request.getParameter(UserConst.GENDER))
                .setActivityLevel(request.getParameter(UserConst.ACTIVITY_LEVEL))
                .setAge(Integer.parseInt(request.getParameter(UserConst.AGE)))
                .build();


    }
}
