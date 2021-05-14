package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

@WebServlet(name = "DesencriptarTexto", urlPatterns = {"/DesencriptarTexto"})
public class DesencriptarTexto extends HttpServlet {

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
        System.out.println("estoy en desencriptar texto");
        request.getRequestDispatcher("textoDesencriptacion.jsp").forward(request, response);
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

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            SecretKeySpec sks = new SecretKeySpec("FooBar1234567890".getBytes("UTF-8"), "AES");
            byte[] iv = new byte[16];
            EncriptarTexto.sr.nextBytes(iv);
            
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] dec = cipher.doFinal(DatatypeConverter.parseBase64Binary(request.getParameter("textoCifrado")));

            String textoFinal = new String(dec);
            request.setAttribute("TextoConvertidoNuevo", textoFinal);
            
            request.getRequestDispatcher("textoDesencriptacion.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorDesencriptar", "Error, formato incorrecto para desencriptar");
            request.getRequestDispatcher("textoDesencriptacion.jsp").forward(request, response);
            System.out.println(ex.getStackTrace());
            System.out.println(ex.getCause());
            System.out.println(ex.getLocalizedMessage());
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
