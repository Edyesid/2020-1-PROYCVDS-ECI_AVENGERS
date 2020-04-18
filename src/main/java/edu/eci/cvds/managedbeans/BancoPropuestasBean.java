package edu.eci.cvds.managedbeans;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.services.BancoPropuestas;
import edu.eci.cvds.services.BancoPropuestasException;

import java.util.List;

import java.sql.Date;



@SuppressWarnings("serial")
@ManagedBean(name="BancoPropuestasBean")
@ApplicationScoped
public class BancoPropuestasBean extends BasePageBean {

    @Inject
    private BancoPropuestas bancoPropuesta;
    private Usuario usuario;

    public List<Usuario> consultarUsuarios(){
        List<Usuario> clientes = null;
        try{
            clientes=bancoPropuesta.consultarUsuarios();
            System.out.println("hola");
            
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
            System.out.println("hola2");
        }
        System.out.println(clientes);
        return clientes;
    }
    public Usuario consultarUsuario(long documento){
        Usuario cliente=null;
        try {
            cliente=bancoPropuesta.consultarUsuario(documento);
        } catch (Exception e) {
            setErrorMessage(e);
        }
        return cliente;
    }
    public void registrarIniciativa(String nombre, String descripcion, String area, Usuario usuario, String estado){
        try{
            bancoPropuesta.registrarIniciativa(new Iniciativa(nombre, descripcion, area, usuario, estado));
        } catch (Exception e) {
            setErrorMessage(e);
        }
    }

    public void setSelectedUsuario(Usuario usuario){this.usuario = usuario;}

    public Usuario GetUsuario(){
        return usuario;
    }
    public List<Iniciativa> consultarIniciativas(){
        List<Iniciativa> iniciativas = null;
        try{
            iniciativas=bancoPropuesta.ConsultarIniciativas();
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
        return iniciativas;
        }
    
    public Iniciativa consultarIniciativa(int id){
        Iniciativa ini=null;
        try {
            ini=bancoPropuesta.consultarIniciativa(id);
        } catch (Exception e) {
            setErrorMessage(e);
        }
        return ini;
    }
    
    private void setErrorMessage(Exception e){
        String message = e.getMessage();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

}