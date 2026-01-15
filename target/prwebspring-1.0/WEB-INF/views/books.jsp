<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Books</title>
    
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
    <link href="css/navbarStyle.css" rel="stylesheet" type="text/css" />
    <link href="css/booksStyle.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="js/jquery-3.7.1.min.js"></script> 
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@include file="navbar.jspf" %>
    
    <div class="py-5">
        <div class="container">
            
            <div class="row">
                <div class="col-md-12">
                    <h1>List of books</h1>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    <div class="table-responsive">
                        
                        <table class="table table-striped table-md sortable">
                            <thead>
                                <tr>
                                    <th scope="col" class="col-md-1">book #</th>
                                    <th scope="col" class="col-md-5">Title</th>
                                    <th scope="col" class="col-md-4">Authors</th>
                                    <th scope="col" class="col-md-2">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${booksList}">
                                    <tr>
                                        <td>${item.bookId}</td>
                                        <td>${item.bookTitle}</td>
                                        <td>${item.bookAuthors}</td>
                                        <td class="text-center">
                                            <form action="" method="POST">
                                                <input type="hidden" name="id" value="${item.bookId}" />
                                                <button class="btn btn-sm btn-warning" formaction="editbook.do" name="edit" title="Edit book">
                                                    <img src="img/edit.png" alt="edit" style="width: 16px; height: 16px;" />
                                                </button>
                                                <button class="btn btn-sm btn-danger" formaction="deletebook.do" name="delete" title="Delete book">
                                                    <img src="img/delete.png" alt="delete" style="width: 16px; height: 16px;" />
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            
                            <tfoot>
                                <tr>
                                    <td colspan="3"></td>
                                    <td class="text-center">
                                        <form action="createbook.do" method="POST">
                                            <button class="btn btn-sm btn-success" name="add" title="Add new book">
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