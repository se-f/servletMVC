package org.todoapp.todoapp.wrappers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.Enumeration;

public class LogRequestWrapper extends HttpServletRequestWrapper {

    public LogRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public void getRequestHeaders() {

        Enumeration<String> headerNames = super.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("Header Name: " + headerName + " value: " + super.getHeader(headerName));
        }

    }

}
