<%-- 
    Document   : list-contacts
    Created on : May 13, 2022, 1:12:17 PM
    Author     : Work
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Task</title>
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    </head>
    <body>
        <div class="container">
            <div class="col-md-offset-1 col-md-10">
                <h2>Mahmoud Mohamad Ali Task</h2>
                <hr />

                <div class="col-sm-2">
                    <input type="button" value="Add Contact"
                           onclick="window.location.href = 'showForm'; return false;"
                           class="btn btn-primary" />
                </div>
                <div class="col-sm-3">
                </div>
                <form action="list" method="get">
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="text" name="text" placeholder="Enter name" value="${text}">
                    </div>
                    <div class="col-sm-1">
                        <!--<a class="btn btn-primary" style="float: right;" href="${findLink}">Find</a>-->
                        <input class="btn btn-primary" style="float: right;" type="submit" />
                    </div>
                </form>
                <br/><br/>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Contact List</div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered">
                            <tr>
                                <th>Photo</th>
                                <th>Name</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="tempContact" items="${contacts}">
                                <c:url var="updateLink" value="/contact/updateForm">
                                    <c:param name="contactId" value="${tempContact.id}" />
                                </c:url>
                                <c:url var="deleteLink" value="/contact/delete">
                                    <c:param name="contactId" value="${tempContact.id}" />
                                </c:url>
                                <tr>
                                    <td><img src="${tempContact.photo}" width="50px" height="50px"></td>
                                    <td>${tempContact.name}</td>
                                    <td>
                                        <a href="${updateLink}">Update</a>
                                        | <a href="${deleteLink}"
                                             onclick="if (!(confirm('Are you sure you want to delete this contact?')))
                                                         return false">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                        <c:if test="${next > maxPages}">
                            <a style="pointer-events: none; color: gray; float: right;">Next</a>
                        </c:if>
                        <c:if test="${next <= maxPages}">
                            <c:url var="nextLink" value="/contact/list">
                                <c:param name="pageNumber" value="${next}" />
                                <c:param name="text" value="${text}" />
                            </c:url>
                            <a style="float: right;" href="${nextLink}">Next</a>
                        </c:if>


                        <c:if test="${previous < 0}">
                            <a style="pointer-events: none; color: gray; float: right; margin-right: 10px;">Previous</a>
                        </c:if>

                        <c:if test="${previous >= 0}">
                            <c:url var="previousLink" value="/contact/list">
                                <c:param name="pageNumber" value="${previous}" />
                                <c:param name="text" value="${text}" />
                            </c:url>
                            <a style="float: right; margin-right: 10px;" href="${previousLink}">Previous</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
