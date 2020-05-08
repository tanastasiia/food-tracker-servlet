package ua.training;

import ua.training.controller.command.*;
import ua.training.controller.utils.Endpoints;
import ua.training.controller.utils.PagesToForward;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

//TODO use patterns in project!!!
//TODO patterns usage in java libraries
//TODO learn how to run from command line (jar)

@WebServlet("/api/*")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put(Endpoints.LOGIN.getPath(), new LoginCommand());
        commands.put(Endpoints.HOME.getPath(), new HomeCommand());
        commands.put(Endpoints.REGISTRATION.getPath(), new RegistrationCommand());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("doGet");
        System.out.println(request);

        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("doPost");
        System.out.println("req: " + request.getRequestURI());

        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println(path);

        Command command = commands.getOrDefault(path, new EmptyCommand());//CommandEnum.valueOf(path).getCommand();//

        PagesToForward page = command.execute(request, response);
        System.out.println(page);

        if (page != null && !page.equals(PagesToForward.EMPTY)) {
            System.out.println("forwarding: " + page.getPath());
            request.getRequestDispatcher(page.getPath()).forward(request, response);
        }
    }


}
