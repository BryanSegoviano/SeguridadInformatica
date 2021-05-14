package servlets;

import daos.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwt.JWT;
import objetosnegocio.Usuario;

@WebServlet(name = "verificarSesion", urlPatterns = {"/verificarSesion"})
public class verificarSesion extends HttpServlet {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("seguridadInformaticaBD");
    UsuarioJpaController usuarioDAO = new UsuarioJpaController(factory);
    Usuario usuarioSesion = new Usuario();

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet verificarSesion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet verificarSesion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String usuario = request.getParameter("txtuser");
        String contrasenia = request.getParameter("txtpass");

        HttpSession session = request.getSession();

        try {
            usuarioSesion = usuarioDAO.consultarUsuarioInicioSesion(usuario, contrasenia);
            String nombreCompleto = usuarioSesion.getNombre() + " " + usuarioSesion.getApellido();
            request.setAttribute("usuario", nombreCompleto);
            
            session.setAttribute("usuario", nombreCompleto);
            String token = JWT.generarJWT(response, usuarioSesion);
            session.setAttribute("token", token);

            response.sendRedirect("MenuPrincipal.jsp");
        } catch (Exception e) {
            session.setAttribute("error", "Error al iniciar sesión, credenciales invalidas");
            response.sendRedirect("InicioSesion.jsp");
        }

        

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
