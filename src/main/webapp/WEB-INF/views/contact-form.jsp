<%-- 
    Document   : contact-form
    Created on : May 13, 2022, 1:46:33 PM
    Author     : Work
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <meta charset="ISO-8859-1">
        <title>Task</title>
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    </head>
    <body>
        <div class="container">
            <div class="col-md-offset-2 col-md-7">
                <h2 class="text-center">Mahmoud Mohamad Ali Task</h2>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Add Contact</div>
                    </div>
                    <div class="panel-body">
                        <form:form action="saveContact" cssClass="form-horizontal"
                                   method="post" modelAttribute="contact">
                            <form:hidden path="id" />
                            <div class="form-group">
                                <label for="name" class="col-md-3 control-label">Name</label>
                                <div class="col-md-9">
                                    <form:input path="name" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="photo" class="col-md-3 control-label">Photo</label>
                                <div class="col-md-9">
                                    <form:input path="photo" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <form:button cssClass="btn btn-primary">Submit</form:button>
                                    </div>
                                </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
