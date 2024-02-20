package org.todoapp.todoapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(
        filterName = "AddNoteFilter",
        urlPatterns = {"/addnote"} // URL patterns of the servlets to be filtered

)
public class NoteFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String noteInput = request.getParameter("noteInput");

        if (noteInput == null || noteInput.trim().isEmpty()) {
            // Note is empty, redirect to home page
            PrintWriter pw = httpResponse.getWriter();
            pw.println("<script>alert('Veuillez inserer une note valide!');window.history.back();</script>");

        } else {
            chain.doFilter(request, response);
        }
    }

}
