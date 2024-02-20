package org.todoapp.todoapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(
        filterName = "SessionFilter",
        urlPatterns = {"/home.jsp", "/getnotes", "/addnote"} // URL patterns of the servlets to be filtered

)
public class SessionFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            // User is not connected, redirect to login page
            PrintWriter pw = httpResponse.getWriter();
            pw.println("<script>alert('Veuillez se connecter!');window.history.back();</script>");

        } else {
            chain.doFilter(request, response);
        }
    }


}
