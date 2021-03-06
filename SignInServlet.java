package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)  {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)  {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        UserProfile profile = accountService.getUserByLogin(login);
        try {
            if (profile == null || !profile.getPass().equals(pass)) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized: " + login);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
