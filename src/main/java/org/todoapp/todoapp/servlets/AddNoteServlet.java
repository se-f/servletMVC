package org.todoapp.todoapp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "AddNoteServlet", value = "/addnote")
public class AddNoteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        String noteInput = request.getParameter("noteInput");
        String username = (String) session.getAttribute("username");

        String isComplete = "0";

        String query = "INSERT INTO todo(id,content,created_by,iscomplete) VALUES(null,?,?,?)";

        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");


        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, noteInput);
            ps.setString(2, username);
            ps.setString(3, isComplete);

            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<script>alert('Note ajouté avec succès!'); window.location.href='getnotes';</script>");

            } else {
                // Display error popup
                pw.println("<script>alert('Echec d'ajout de note!');</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Display error popup with message and redirect to homepage
            System.out.println(e);
            pw.println("<script>alert('Echec ajout!'); window.location.href='home.jsp';</script>");
        }

    }

}