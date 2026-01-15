<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrowing Page</title>
    
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
    <link href="css/navbarStyle.css" rel="stylesheet" type="text/css" />
    <link href="css/borrowStyle.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="js/jquery-3.7.1.min.js"></script> 
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@include file="navbar.jspf" %>
    
    <div class="py-5">
        <div class="container">
            <h1>Borrowing page</h1>
            
            <div class="row mb-4">
                <div class="col-md-12">
                    <p><strong>FirstName:</strong> ${user.personFirstname}</p>
                    <p><strong>LastName:</strong> ${user.personLastname}</p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr class="table-success">
                                    <th>Date</th>
                                    <th>Title</th>
                                    <th>Return</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="borrow" items="${user.bookCollection}">
                                    <tr>
                                        <td><fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd" /></td>
                                        <td>${borrow.bookId.bookTitle}</td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${empty borrow.borrowReturn}">
                                                    <!-- Livre non retourné - afficher le bouton de retour -->
                                                    <form action="returnBorrow.do" method="POST">
                                                        <input type="hidden" name="id" value="${borrow.borrowId}" />
                                                        <input type="hidden" name="userID" value="${user.personId}" />
                                                        <button type="submit" class="btn btn-sm btn-warning" title="Return this book">
                                                            <img src="img/return.png" alt="return" style="width: 16px; height: 16px;" />
                                                        </button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <!-- Livre déjà retourné - afficher la date -->
                                                    <fmt:formatDate value="${borrow.borrowReturn}" pattern="yyyy-MM-dd" />
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="3" class="p-3">
                                        <form action="addBorrow.do" method="POST" class="d-flex align-items-center">
                                            <input type="hidden" name="userID" value="${user.personId}" />
                                            
                                            <select name="bookID" class="form-control me-2" required>
                                                <option value="" disabled selected>-- Select a book to borrow --</option>
                                                <c:forEach var="book" items="${booksList}">
                                                    <option value="${book.bookId}">${book.bookTitle} - ${book.bookAuthors}</option>
                                                </c:forEach>
                                            </select>
                                            
                                            <button type="submit" class="btn btn-success">
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