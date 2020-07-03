package ua.training.controller.command;

import ua.training.controller.PagesToForward;
import ua.training.controller.Routes;
import ua.training.model.entity.User;
import ua.training.service.UserService;
import ua.training.utils.ControllerUtil;
import ua.training.utils.validation.ValidationErrorMessages;
import ua.training.utils.validation.ValidationException;

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

        try {
            User newUser = controllerUtil.parseUserUpdate(request);
            UserService.getInstance().updateAccount(newUser, controllerUtil.getUser(request));

            response.sendRedirect(Routes.ACCOUNT.getPath());
            return PagesToForward.NONE;

        } catch (ValidationException e) {
            controllerUtil.setErrorAttributes(request, e.getErrors());
        } catch (Exception e){
            request.setAttribute("user_input_error", ValidationErrorMessages.INCORRECT_INPUT);
        }
        return PagesToForward.CHANGE_ACCOUNT;
    }

}
