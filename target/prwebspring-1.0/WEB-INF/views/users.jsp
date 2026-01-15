<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Users</title>
     <link href="css/navbarStyle.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
    <script type="text/javascript" src="js/jquery-3.7.1.min.js"></script> 
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
    <link href="css/usersStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%@include file="navbar.jspf" %>
    
    <div class="py-5">
        <div class="container">
            
            <div class="row">
                <div class="col-md-12">
                    <h1>List of users</h1>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    <div class="table-responsive">
                        
                        <table class="table table-striped table-md sortable">
                            <thead>
                                <tr>
                                    <th scope="col" class="col-md-2">user #</th>
                                    <th scope="col" class="col-md-3">FirstName</th>
                                    <th scope="col" class="col-md-3">LastName</th>
                                    <th scope="col" class="col-md-2">Birthdate</th>
                                    <th scope="col" class="col-md-2"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${usersList}">
                                    <tr>
                                        <td>${item.personId}</td>
                                        <td>${item.personFirstname}</td>
                                        <td>${item.personLastname}</td>
                                        <td><fmt:formatDate value="${item.personBirthdate}" pattern="yyyy-MM-dd" /></td>
                                        <td class="text-center">
                                            <form action="" method="POST">
                                                <input type="hidden" name="id" value="${item.personId}" />
                                                <button class="btn btn-sm btn-warning" formaction="edituser.do" name="edit" title="Edit user">
                                                    <img src="img/edit.png" alt="edit" style="width: 16px; height: 16px;" />
                                                </button>
                                                <button class="btn btn-sm btn-danger" formaction="deleteuser.do" name="delete" title="Delete user">
                                                    <img src="img/delete.png" alt="delete" style="width: 16px; height: 16px;" />
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            
                            <tfoot>
                                <tr id="addNew">
                                    <td colspan="4"></td>
                                    <td class="text-center">
                                        <form action="createuser.do" method="POST">
                                            <button class="btn btn-sm btn-success" name="add" title="Add new user">
                                                <img src="img/plus.png" alt="add" style="width: 16px; height: 16px;" />
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>