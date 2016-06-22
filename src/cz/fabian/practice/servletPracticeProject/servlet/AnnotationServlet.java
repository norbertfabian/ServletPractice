package cz.fabian.practice.servletPracticeProject.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by nfabian on 22.6.16.
 */

@WebServlet(name = "AnnotationServlet" , urlPatterns = {"/AnnotationServletPath"},
        initParams = {@WebInitParam(name = "initParamValue", value = "Init param value")})
public class AnnotationServlet extends HttpServlet {

    public void init() {
        this.getServletContext().setAttribute("contextValue", "shared value from init method");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        HttpSession session = request.getSession();
        saveUserNameToSession(userName, session);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h3>Hello " + session.getAttribute("userName")   + " from AnnotationServlet doPost!</h3>");
        String fullName = request.getParameter("fullName");
        writer.println("Your full name is " + fullName);
        String profession = request.getParameter("prof");
        writer.println("Your profession is " + profession);
        String[] foods = request.getParameterValues("food");
        writer.println("You have " + foods.length + " favorite foods.");
        for(String food : foods) {
            writer.println(food);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        HttpSession session = request.getSession();
        saveUserNameToSession(userName, session);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h3>Hello " + session.getAttribute("userName") + " from AnnotationServlet doGet!</h3>");

        writer.println("Value stored in the context is:" + this.getServletContext().getAttribute("contextValue"));
        writer.println("Web init param value is: " + this.getServletConfig().getInitParameter("initParamValue"));
    }

    private void saveUserNameToSession(String userName, HttpSession session) {
        if (userName != null && userName != "") {
            session.setAttribute("userName", userName);
        }
    }
}
