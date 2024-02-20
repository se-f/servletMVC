package org.todoapp.todoapp.servlets;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.todoapp.todoapp.beans.ToDoBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GetNotesServlet", value = "/getnotes")
public class GetNotesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Create an ArrayList to store the notes of type ToDoBean
        ArrayList<ToDoBean> notes = new ArrayList<>();

        // Get username from session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Get the DataSource from the ServletContext in order to get the connection
        DataSource dataSource = (DataSource) request.getServletContext().getAttribute("dbConnection");

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM todo WHERE created_by = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String content = resultSet.getString("content");
                        String isComplete = resultSet.getString("iscomplete");
                        notes.add(new ToDoBean(content, username, isComplete));
                    }
                    session.setAttribute("notes", notes);
                    response.sendRedirect("home.jsp");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
