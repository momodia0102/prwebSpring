<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Users</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"> 
    <%-- <script type="text/javascript" src="js/jquery-3.7.1.min.js"></script> --%>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/usersStyle.css" rel="stylesheet" type="text/css" />
    </head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.html">Library App</a> 

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav ms-auto"> 
                    <a class="nav-link text-white" href="users.html">Users</a>

                    <a class="nav-link text-white" href="books.html">Books</a>

                    <a class="nav-link btn btn-outline-danger ms-2" href="index.html">Logout</a>
                </div>
            </div>
        </div>
    </nav>
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
                                <tr>
                                    <td>1</td>
                                    <td>Jean-Yves</td>
                                    <td>Martin</td>
                                    <td>1963-08-12</td>
                                    <td class="text-center">
                                        <form action="" method="POST">
                                            <button class="btn btn-sm btn-warning" formaction="editUser" name="edit" title="Edit user">
                                                <span class="icon">âï¸</span>
                                            </button>
                                            <button class="btn btn-sm btn-danger" formaction="deleteUser" name="delete" title="Delete user">
                                                <span class="icon">ðï¸</span>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Marie</td>
                                    <td>Dubois</td>
                                    <td>1985-03-25</td>
                                    <td class="text-center">
                                        <form action="" method="POST">
                                            <button class="btn btn-sm btn-warning" formaction="editUser" name="edit" title="Edit user">
                                                <span class="icon">âï¸</span>
                                            </button>
                                            <button class="btn btn-sm btn-danger" formaction="deleteUser" name="delete" title="Delete user">
                                                <span class="icon">ðï¸</span>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                            
                            <tfoot>
                                <tr id="addNew">
                                    <td colspan="4"></td>
                                    <td class="text-center">
                                        <form action="createUser" method="POST">
                                            <button class="btn btn-sm btn-success" name="add" title="Add new user">
                                                <span class="icon">â</span>
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