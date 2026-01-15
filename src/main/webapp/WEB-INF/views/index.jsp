<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Login</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <script type="text/javascript" src="js/jquery-3.7.1.min.js"></script> 
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css"> 
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link href="../style/indexStyle.css" rel="stylesheet" type="text/css" />
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    
    <div class="py-5">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="text-center">Library Login</h2>
                </div>
            </div>
            
            <div class="row justify-content-center"> 
                <div class="col-md-6 col-lg-5"> 
                    <form action="login.do" method="POST">
                        
                        <div class="form-group row mb-3">
                            <label for="inputLogin" class="col-sm-3 col-form-label">Login</label>
                            <div class="col-sm-9">
                                <input type="text" 
                                       class="form-control" 
                                       id="inputLogin" 
                                       name="login" 
                                       placeholder="Login" 
                                       required="required" />
                            </div>
                        </div>
                        
                        <div class="form-group row mb-3">
                            <label for="inputPassword" class="col-sm-3 col-form-label">Password</label>
                            <div class="col-sm-9">
                                <input type="password" 
                                       class="form-control" 
                                       id="inputPassword" 
                                       name="password" 
                                       placeholder="Password" 
                                       required="required" />
                            </div>
                        </div>
                        
                        <div class="form-group row">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-success btn-lg mt-3">Login</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>