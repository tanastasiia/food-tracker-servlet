package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;
import ua.training.controller.Endpoints;
import ua.training.model.dto.UserUpdateDto;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.controller.ControllerUtil;
import ua.training.utils.validation.ValidationErrorMessages;
import ua.training.utils.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.ServerException;

/**
 * Command for adding food
 *
 * @see Endpoints#ADD_FOOD
 */
public class ChangeAccountCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private Logger logger = LogManager.getLogger(ChangeAccountCommand.class.getName());

    @Override
    public Paths execute(HttpServletRequest request, HttpServletResponse response) throws ServerException {

        if (controllerUtil.isMethodGet(request)) {
            return PagesToForward.CHANGE_ACCOUNT;
        }

        try {
            UserUpdateDto userUpdateDto = controllerUtil.parseUserUpdate(request);

            User updatedUser = UserService.getInstance().updateAccount(userUpdateDto, controllerUtil.getUser(request));

            controllerUtil.setUser(request, updatedUser);

            return Endpoints.ACCOUNT;

        } catch (ValidationException e) {
            logger.info("Validation errors: " + e.getErrors());
            controllerUtil.setErrorAttributes(request, e.getErrors());
        } catch (NumberFormatException e){
            logger.info("Exception: " + e);
            request.setAttribute("user_input_error", ValidationErrorMessages.INCORRECT_INPUT);
        }
        return PagesToForward.CHANGE_ACCOUNT;
    }

}
