package ua.training;

//import org.apache.logging.log4j.LogManager;

import org.apache.log4j.LogManager;
import ua.training.controller.command.*;
import ua.training.controller.Endpoints;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/foodtracker/*")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) {

        commands.put(Endpoints.LOGIN.getPath(), new LoginCommand());
        commands.put(Endpoints.LOGOUT.getPath(), new LogoutCommand());
        commands.put(Endpoints.HOME.getPath(), new HomeCommand());
        commands.put(Endpoints.ADD_MEAL.getPath(), new AddMealCommand());
        commands.put(Endpoints.ADD_FOOD.getPath(), new AddFoodCommand());
        commands.put(Endpoints.REGISTRATION.getPath(), new RegistrationCommand());
        commands.put(Endpoints.ACCOUNT.getPath(), new AccountCommand());
        commands.put(Endpoints.STATISTICS.getPath(), new StatisticsCommand());
        commands.put(Endpoints.ADMIN.getPath(), new AdminPageCommand());
        commands.put(Endpoints.CHANGE_ACCOUNT.getPath(), new ChangeAccountCommand());
        commands.put(Endpoints.CHANGE_USER_ROLE.getPath(), new ChangeUserRoleCommand());
        commands.put(Endpoints.CHANGE_USER_PASSWORD.getPath(), new ChangePasswordCommand());
        commands.put(Endpoints.CHANGE_FOOD.getPath(), new ChangeFoodCommand());
        commands.put(Endpoints.GET_FOOD_NAMES.getPath(), new GetFoodNamesCommand());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI();

        Command command = commands.getOrDefault(path, new EmptyCommand());

        try {
            Paths page = command.execute(request, response);
            if (!page.equals(PagesToForward.NONE)) {
                page.go(request, response);
            }
        } catch (Exception e) {
            LogManager.getRootLogger().error(e);
            PagesToForward.ERROR.go(request, response);
        }
    }


}
