package org.todoapp.todoapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(
        filterName = "ValidationFilter",
        urlPatterns = {"/loginservice"} // URL patterns of the servlets to be filtered

)
public class LoginFormFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if form data is valid
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            // Form data is invalid
            PrintWriter pw = httpResponse.getWriter();
            pw.println("<html><body><p>Veuillez saisir vos donnees correctement!</p></body></html>");

        } else {
            // Form data is valid, redirect to next servlet or filter
            chain.doFilter(request, response);
        }
    }

}
