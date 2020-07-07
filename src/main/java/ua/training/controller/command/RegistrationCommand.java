package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.PagesToForward;
import ua.training.model.entity.User;
import ua.training.service.UserService;
import ua.training.utils.ControllerUtil;
import ua.training.utils.validation.ValidationErrorMessages;
import ua.training.utils.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private ControllerUtil controllerUtil = ControllerUtil.getInst();
    private Logger logger = LogManager.getLogger(RegistrationCommand.class.getName());

    @Override
    public PagesToForward execute(HttpServletRequest request, HttpServletResponse response) {

        if (controllerUtil.isMethodGet(request)) {
            return PagesToForward.REGISTRATION;
        }

        try {
            User user = controllerUtil.parseRegUser(request);
            request.setAttribute("success", UserService.getInstance().register(user));
        } catch (ValidationException e) {
            logger.info("Validation errors: " + e.getErrors());
            controllerUtil.setErrorAttributes(request, e.getErrors());
        } catch (Exception e) {
            logger.info("Exception: " + e);
            request.setAttribute("reg_input_error", ValidationErrorMessages.INCORRECT_INPUT);
        }
        return PagesToForward.REGISTRATION;
    }

}
