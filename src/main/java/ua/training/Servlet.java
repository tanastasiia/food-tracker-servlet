package ua.training;

//import org.apache.logging.log4j.LogManager;
import ua.training.controller.command.*;
import ua.training.controller.Routes;
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

//TODO use patterns in project!!!
//TODO patterns usage in java libraries
//TODO learn how to run from command line (jar)

@WebServlet("/foodtracker/*")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        commands.put(Routes.LOGIN.getPath(), new LoginCommand());
        commands.put(Routes.LOGOUT.getPath(), new LogoutCommand());
        commands.put(Routes.HOME.getPath(), new HomeCommand());
        commands.put(Routes.ADD_MEAL.getPath(), new AddMealCommand());
        commands.put(Routes.ADD_FOOD.getPath(), new AddFoodCommand());
        commands.put(Routes.REGISTRATION.getPath(), new RegistrationCommand());
        commands.put(Routes.ACCOUNT.getPath(), new AccountCommand());
        commands.put(Routes.STATISTICS.getPath(), new StatisticsCommand());
        commands.put(Routes.ADMIN.getPath(), new AdminPageCommand());
        commands.put(Routes.CHANGE_ACCOUNT.getPath(), new ChangeAccountCommand());
        commands.put(Routes.CHANGE_USER_ROLE.getPath(), new ChangeUserRoleCommand());


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        Command command = commands.getOrDefault(path, new EmptyCommand());

        Paths page = command.execute(request, response);

        if (!page.equals(PagesToForward.NONE)) {
            request.getRequestDispatcher(page.getPath()).forward(request, response);
        }
    }



}
