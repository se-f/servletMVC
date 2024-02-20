package org.todoapp.todoapp.wrappers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class LogResponseWrapper extends HttpServletResponseWrapper {


    public LogResponseWrapper(HttpServletResponse response) {
        super(response);
        logResponseContent();
    }

    public String logResponseContent() {
        // Return the response content and headers
        
        String responseContent = String.valueOf(super.getStatus());
        String responseHeaders = super.getHeaderNames().toString();

        return "status : " + responseContent + ", Headers: " + responseHeaders;
    }
}
