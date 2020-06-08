package ua.training.controller.command;

import ua.training.utils.ControllerUtil;
import ua.training.controller.PagesToForward;
import ua.training.controller.Routes;
import ua.training.model.constants.UserConst;
import ua.training.model.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeAccountCommand implements Command {
    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (controllerUtil.isMethodGet(request)) {
            return PagesToForward.CHANGE_ACCOUNT;
        }

        User newUser = parseForm(request);
        UserService.getInstance().updateAccount(newUser, controllerUtil.getUser(request));

        response.sendRedirect(Routes.ACCOUNT.getPath());
        return PagesToForward.NONE;
    }

    private User parseForm(HttpServletRequest request) {
        return new User.Builder()
                .setFirstName(request.getParameter(UserConst.FIRST_NAME.getField()))
                .setLastName(request.getParameter(UserConst.LAST_NAME.getField()))
                .setHeight(Integer.parseInt(request.getParameter(UserConst.HEIGHT.getField())))
                .setWeight(Integer.parseInt(request.getParameter(UserConst.WEIGHT.getField())))
                .setGender(request.getParameter(UserConst.GENDER.getField()))
                .setActivityLevel(request.getParameter(UserConst.ACTIVITY_LEVEL.getField()))
                .setAge(Integer.parseInt(request.getParameter(UserConst.AGE.getField())))
                .build();


    }
}
