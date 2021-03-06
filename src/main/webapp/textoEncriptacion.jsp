<%@page import="jwt.JWT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    if (JWT.validarJWT(request, response)) {

    } else {
        session = request.getSession();
        session.removeAttribute("token");
        response.sendRedirect("InicioSesion.jsp");
        //request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
    }

%>
<!DOCTYPE html>
<html>

    <head>
        <title>Encriptador</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.24/datatables.min.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
    </head>

    <body>
        <article>
            <h1 class="text-center">Encriptador de mensajes</h1>
            <hr />
            <hr />
            <div class="container-fluid">
                <div class="row align-items-center justify-content-center">

                    <form class="form-sign" action="ControlServlet" method="POST">
                        <div class="m-3 text-center">                                                     
                            <textarea name="textoPlano" rows="5" cols="80" style="overflow:auto;resize:none" placeholder="                                       Escribe aquí el mensaje a encriptar" required></textarea>
                            <br><br>
                            <img width="100" height="100"  src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNDkwLjY2NyA0OTAuNjY3IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA0OTAuNjY3IDQ5MC42Njc7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxwYXRoIHN0eWxlPSJmaWxsOiMyMTk2RjM7IiBkPSJNMjQ1LjMzMywwQzEwOS44MzksMCwwLDEwOS44MzksMCwyNDUuMzMzczEwOS44MzksMjQ1LjMzMywyNDUuMzMzLDI0NS4zMzMNCglzMjQ1LjMzMy0xMDkuODM5LDI0NS4zMzMtMjQ1LjMzM0M0OTAuNTE0LDEwOS45MDMsMzgwLjc2NCwwLjE1MywyNDUuMzMzLDB6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojRkFGQUZBOyIgZD0iTTMxNy44NjcsMjIxLjg2N2wtMTkuMiwxOS4yVjExNy4zMzNjMC0yOS40NTUtMjMuODc4LTUzLjMzMy01My4zMzMtNTMuMzMzUzE5Miw4Ny44NzgsMTkyLDExNy4zMzMNCgl2MTIzLjU4NGwtMTkuMi0xOS4yYy0xOS4xMzktMTguMjg5LTQ5LjI3Ny0xOC4yODktNjguNDE2LDBjLTE4Ljg4OSwxOC44OTQtMTguODg5LDQ5LjUyMiwwLDY4LjQxNmwxMTguMjUxLDExOC4yNTENCgljMTIuNDk2LDEyLjQ5MiwzMi43NTIsMTIuNDkyLDQ1LjI0OCwwbDExOC4yNTEtMTE4LjI1MWMxOC44ODktMTguODk0LDE4Ljg4OS00OS41MjIsMC02OC40MTYNCglDMzY2Ljk3NSwyMDMuNTY1LDMzNi45NDYsMjAzLjYzMSwzMTcuODY3LDIyMS44Njd6Ii8+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8L3N2Zz4NCg==" /> 
                            <br><br>
                            <textarea name="textoEncriptado" rows="5" cols="80" readonly="" style="overflow:auto;resize:none">${textoConvertido}</textarea>
                        </div>
                        <div class="m-3 text-center">
                            <div class=" gap-6 mx-auto form-group">
                                <button class="btn btn-secondary btn-lg" type="submit" name="accion" value="encriptar">Encriptar mensaje</button>
                            </div>
                        </div>
                    </form>

                    <div class="m-3 mt-5 text-center">
                        <div class=" gap-6 mx-auto">
                            <button class="btn btn-danger btn-lg" onclick="location.href = 'MenuPrincipal.jsp'">Cancelar</button>
                        </div>
                        
                    </div>
                        



                </div>
            </div>            

        </article>


        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.24/datatables.min.js"></script>
        <script src="js/app.js" charset="utf-8"></script>
    </body>

</html>
