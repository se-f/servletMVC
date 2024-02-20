package org.todoapp.todoapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.todoapp.todoapp.wrappers.LogRequestWrapper;
import org.todoapp.todoapp.wrappers.LogResponseWrapper;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Wrap request for logging
        LogRequestWrapper loggingRequest = new LogRequestWrapper((HttpServletRequest) request);

        // Wrap response for logging
        LogResponseWrapper loggingResponse = new LogResponseWrapper((HttpServletResponse) response);

        // Pass wrapped request and response to the next filter or servlet
        chain.doFilter(loggingRequest, loggingResponse);

        // Logging request and response data (post-treatment)
        logRequestData(loggingRequest);
        logResponseData(loggingResponse);
    }

    private void logRequestData(LogRequestWrapper loggingRequest) {
        System.out.println("______\nRequest Headers: \n");
        loggingRequest.getRequestHeaders();
    }

    private void logResponseData(LogResponseWrapper loggingResponse) {
        String response = loggingResponse.logResponseContent();
        System.out.println("RESPONSE NUMBER: " + response);
        System.out.println("______\n");

    }

}
