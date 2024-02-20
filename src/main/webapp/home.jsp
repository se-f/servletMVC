<%@ page import="java.util.List" %>
<%@ page import="org.todoapp.todoapp.beans.ToDoBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>To-Do Notes</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>To-Do Notes</h2>

    <!-- Form for adding notes -->
    <form action="addnote" method="post" class="mb-3">
        <div class="form-group">
            <label for="noteInput">Add Note:</label>
            <input type="text" class="form-control" id="noteInput" name="noteInput" placeholder="Enter your note">
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <!-- To-Do List -->
    <ul class="list-group">
        <%
            List<ToDoBean> notes = (List<ToDoBean>) session.getAttribute("notes");
            if (notes != null) {
                for (int i = 0; i < notes.size(); i++) {
                    String content = notes.get(i).getContent();
                    String liClass = (notes.get(i).getIsComplete().equals("1")) ? "bg-success text-white" : "";
        %>
        <li class="list-group-item <%= liClass %>">
            <div class="d-flex justify-content-between align-items-center">
                <span><%= content %></span>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-success btn-sm">Complete</button>
                    <button type="button" class="btn btn-danger btn-sm">Delete</button>
                </div>
            </div>
        </li>
        <%
                }
            }
        %>
    </ul>

</div>


</body>
</html>
