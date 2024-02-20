package org.todoapp.todoapp.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;


public class DBConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Initialize database connection
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3308/todo");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        // Set the DataSource as an attribute in the ServletContext
        context.setAttribute("dbConnection", dataSource);

        System.out.println("Database connection initialized.");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Close the database connection when destroying context
        BasicDataSource dataSource = (BasicDataSource) context.getAttribute("dbConnection");
        if (dataSource != null) {
            try {
                dataSource.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
