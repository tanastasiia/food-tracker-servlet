package ua.training;

//import org.apache.logging.log4j.LogManager;

import org.apache.log4j.LogManager;
import ua.training.controller.command.*;
import ua.training.controller.RoutesToRedirect;
import ua.training.controller.PagesToForward;
import ua.training.controller.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/foodtracker/*")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) {

        commands.put(RoutesToRedirect.LOGIN.getPath(), new LoginCommand());
        commands.put(RoutesToRedirect.LOGOUT.getPath(), new LogoutCommand());
        commands.put(RoutesToRedirect.HOME.getPath(), new HomeCommand());
        commands.put(RoutesToRedirect.ADD_MEAL.getPath(), new AddMealCommand());
        commands.put(RoutesToRedirect.ADD_FOOD.getPath(), new AddFoodCommand());
        commands.put(RoutesToRedirect.REGISTRATION.getPath(), new RegistrationCommand());
        commands.put(RoutesToRedirect.ACCOUNT.getPath(), new AccountCommand());
        commands.put(RoutesToRedirect.STATISTICS.getPath(), new StatisticsCommand());
        commands.put(RoutesToRedirect.ADMIN.getPath(), new AdminPageCommand());
        commands.put(RoutesToRedirect.CHANGE_ACCOUNT.getPath(), new ChangeAccountCommand());
        commands.put(RoutesToRedirect.CHANGE_USER_ROLE.getPath(), new ChangeUserRoleCommand());
        commands.put(RoutesToRedirect.CHANGE_USER_PASSWORD.getPath(), new ChangePasswordCommand());
        commands.put(RoutesToRedirect.CHANGE_FOOD.getPath(), new ChangeFoodCommand());
        commands.put(RoutesToRedirect.GET_FOOD_NAMES.getPath(), new GetFoodNamesCommand());

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
