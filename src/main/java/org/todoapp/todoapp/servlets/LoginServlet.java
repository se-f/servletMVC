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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/loginservice")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (isValidUser(username, password)) {
            // Get username from session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);


            response.sendRedirect("getnotes");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<html><body><p>Login failed. Invalid username or password.</p></body></html>");
        }
    }

    private boolean isValidUser(String username, String password) {

        // Get the DataSource from the ServletContext in order to get the connection
        DataSource dataSource = (DataSource) getServletContext().getAttribute("dbConnection");

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If there is a matching user in the database
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
