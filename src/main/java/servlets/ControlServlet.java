package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwt.JWT;


public class ControlServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String accion = request.getParameter("accion");

        if (accion.equalsIgnoreCase("Ingresar")) {
            //response.sendRedirect("validarSesion");
            request.getRequestDispatcher("verificarSesion").forward(request, response);
        } else if (accion.equalsIgnoreCase("Salir")) {
            HttpSession session = request.getSession();
            session.removeAttribute("token");
            session.invalidate();
            request.getRequestDispatcher("InicioSesion.jsp").forward(request, response);

        } else {

            if (JWT.validarJWT(request, response)) {

                // Envia a pantallas
                if (accion.equals("regresarMenuPrincipal")) {
                    request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
                } else if (accion.equals("textoEncriptacion")) {
                    request.getRequestDispatcher("textoEncriptacion.jsp").forward(request, response);
                } else if (accion.equals("textoDesencriptacion")) {
                    request.getRequestDispatcher("textoDesencriptacion.jsp").forward(request, response);
                } 

//                    // Envia a Servlets
                else if (accion.equals("encriptar")) {
//                    response.sendRedirect("EncriptarTexto");
                    request.getRequestDispatcher("EncriptarTexto").forward(request, response);
                }
                else if (accion.equals("desencriptar")) {
//                    response.sendRedirect("DesencriptarTexto");
                    request.getRequestDispatcher("DesencriptarTexto").forward(request, response);
                }
//                else if (accion.equals("importarAlumnos")) {
//                    response.sendRedirect("importarAlumnos");
//                    //request.getRequestDispatcher("importarAlumnos").forward(request, response);
//                } else if (accion.equals("registrarEscuelas")) {
//                    //response.sendRedirect("importarAlumnos");
//                    request.getRequestDispatcher("registrarEscuelas").forward(request, response);
//                } else if (accion.equals("registrarUsuario")) {
//                    //response.sendRedirect("importarUsuario");
//                    request.getRequestDispatcher("registrarUsuario").forward(request, response);
//                }

            } else {
                request.setAttribute("error", "Tiempo expirado. Volver a iniciar sesión.");
                request.getRequestDispatcher("InicioSesion.jsp").forward(request, response);
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
