<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create / Edit User page</title>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> 
    <link href="css/navbarStyle.css" rel="stylesheet" type="text/css" />
    <link href="css/userStyle.css" rel="stylesheet" type="text/css" />
    
    <script src="js/jquery-3.7.1.min.js"></script> 
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</head>

<body>
    <%@include file="navbar.jspf" %>

    <div class="py-5">
        <div class="container">
            <div class="row mb-4">
                <div class="col-md-12">
                    <h1>Create / Edit User page</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <form action="saveuser.do" method="POST">
                        <div class="table-responsive">
                            <table class="table table-striped align-middle">
                                <tbody>
                                    <tr>
                                        <th class="col-md-4">User #</th>
                                        <td>
                                          <c:choose>
                                            <c:when test="${(empty user) || (empty user.personId)}">NEW<input type="hidden" name="id" value="-1" /></c:when>
                                            <c:otherwise>${user.personId}<input type="hidden" name="id" value="${user.personId}" /></c:otherwise>
                                          </c:choose> 
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>First Name</th>
                                        <td>
                                            <input type="text" 
                                                   class="form-control" 
                                                   name="firstName" 
                                                   value="${user.personFirstname}" 
                                                   required />
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>Last Name</th>
                                        <td>
                                            <input type="text" 
                                                   class="form-control" 
                                                   name="lastName" 
                                                   value="${user.personLastname}" 
                                                   required />
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>Birthdate</th>
                                        <td>
                                            <fmt:formatDate value="${user.personBirthdate}" 
                                                            pattern="yyyy-MM-dd" 
                                                            var="birthdateFormatted"/>
                                            <input type="date" 
                                                   class="form-control" 
                                                   name="birthdate" 
                                                   value="${birthdateFormatted}" 
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

    <!-- Section des emprunts - affichée uniquement si l'utilisateur existe -->
    <c:if test="${!empty user && !empty user.personId}">
        <div class="py-3">
            <div class="container">
                <div class="row mb-3">
                    <div class="col-md-12">
                        <h2>Borrowings</h2>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table table-striped align-middle">
                                <thead class="table-success">
                                    <tr>
                                        <th scope="col">Date</th>
                                        <th scope="col">Title</th>
                                        <th scope="col" class="text-center">Return</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="borrow" items="${user.borrowCollection}">
                                        <tr>
                                            <td>
                                                <fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd" />
                                            </td>
                                            <td>${borrow.bookId.bookTitle}</td>
                                            <td class="text-center">
                                                <c:choose>
                                                    <c:when test="${!empty borrow.borrowReturn}">
                                                        <fmt:formatDate value="${borrow.borrowReturn}" pattern="yyyy-MM-dd" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form action="returnBorrow.do" method="POST" style="display: inline;">
                                                            <input type="hidden" name="id" value="${borrow.borrowId}" />
                                                            <input type="hidden" name="userID" value="${user.personId}" />
                                                            <button type="submit" class="btn btn-sm btn-warning" title="Return this book">
                                                                <img src="img/return.png" alt="return" style="width: 16px; height: 16px;" />
                                                            </button>
                                                        </form>
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
    </c:if>

</body>
</html>