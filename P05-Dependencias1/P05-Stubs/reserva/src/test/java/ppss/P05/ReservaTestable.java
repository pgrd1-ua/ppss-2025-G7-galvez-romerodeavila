package ppss.P05;

import java.util.Objects;

public class ReservaTestable extends Reserva{
    String loginOK, passwordOK;
    Usuario tipoUsuOK;
    public void setLoginOK(String login){
        loginOK = login;
    }
    public void setPasswordOK(String password){
        passwordOK = password;
    }
    public void setTipoUsuOK(Usuario user){
        tipoUsuOK = user;
    }
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        return Objects.equals(loginOK, login) && Objects.equals(passwordOK, password) && tipoUsuOK == tipoUsu;
    }
    public void setOperacion(Operacion operacion){
        this.operacion = operacion;
    }
}
