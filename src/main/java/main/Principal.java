package main;

import daos.UsuarioJpaController;
import objetosnegocio.Usuario;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principal {

    public static void main(String[] args) {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("seguridadInformaticaBD");
        UsuarioJpaController usuarioDAO = new UsuarioJpaController(factory);
        
        Usuario usuario = new Usuario("pancho2012", "panchopa", "Pancho Sergio", "Villa", "Hermosillo", "Sonora");
        
        usuarioDAO.create(usuario);
    }
    
}
