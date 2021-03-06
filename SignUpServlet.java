package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        UserProfile profile = new UserProfile(login, pass);

        try {

            if (profile.getLogin().contentEquals("") || profile.getPass().contentEquals("")) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Error!\nEmpty fields found\nPlease try again.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            } else {
                accountService.addNewUser(profile);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Success! Created new account : Login = " + login + " Password = " + pass);
                response.setStatus(HttpServletResponse.SC_CREATED);

            }
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
