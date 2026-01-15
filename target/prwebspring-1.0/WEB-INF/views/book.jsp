<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create / Edit Book page</title>
    
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
    <link href="css/navbarStyle.css" rel="stylesheet" type="text/css" />
    <link href="css/bookStyle.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="js/jquery-3.7.1.min.js"></script> 
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@include file="navbar.jspf" %>
    
    <div class="py-5">
        <div class="container">
            
            <div class="row mb-4">
                <div class="col-md-12">
                    <h1>Create / Edit Book page</h1>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    
                    <form action="savebook.do" method="POST">
                        <div class="table-responsive">
                            
                            <table class="table table-striped align-middle">
                                <tbody>
                                    
                                    <tr>
                                        <th class="col-md-4">Book #</th>
                                        <td>
                                            <c:choose>
                                                <c:when test="${(empty book) || (empty book.bookId)}">
                                                    NEW<input type="hidden" name="id" value="-1" />
                                                </c:when>
                                                <c:otherwise>
                                                    ${book.bookId}<input type="hidden" name="id" value="${book.bookId}" />
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <th>Title</th>
                                        <td>
                                            <input type="text" 
                                                   class="form-control" 
                                                   name="title" 
                                                   value="${book.bookTitle}" 
                                                   required />
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <th>Authors</th>
                                        <td>
                                            <input type="text" 
                                                   class="form-control" 
                                                   name="authors" 
                                                   value="${book.bookAuthors}" 
                                                   required />
                                        </td>
                                    </tr>
                                </tbody>
                                
                                <tfoot>
                                    <tr>
                                        <td colspan="2" class="text-center">
                                            <button type="submit" class="btn btn-primary mt-3 px-5">
                                                Save
                                            </button>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>