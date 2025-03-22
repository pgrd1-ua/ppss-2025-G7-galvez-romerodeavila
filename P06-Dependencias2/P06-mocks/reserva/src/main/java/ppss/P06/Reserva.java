package ppss.P06;

import ppss.IOperacionBO;
import ppss.excepciones.*;
import java.util.ArrayList;

public class Reserva {
    FactoriaBOs fd = new FactoriaBOs();
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void setFactoriaBOs(FactoriaBOs fd){
        this.fd = fd;
    }
    public void realizaReserva(String login, String password, String socio, String [] isbns) throws ReservaException {
        ArrayList<String> errores = new ArrayList<String>();
        if(!compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)) { //D.E de la clase local
            errores.add("ERROR de permisos");
        }
        else {
            //FactoriaBOs fd = new FactoriaBOs(); //D.E de la clase local
            FactoriaBOs fd = this.fd;
            IOperacionBO io = fd.getOperacionBO(); //D.E de tipo FactoriaB0s
            try {
                for(String isbn: isbns) {
                    try {
                        io.operacionReserva(socio, isbn); //D.E
                    }
                    catch (IsbnInvalidoException iie) {
                        errores.add("ISBN invalido" + ":" + isbn);
                    }
                }
            } catch (SocioInvalidoException sie) {
                errores.add("SOCIO invalido");
            } catch (JDBCException je) {
                errores.add("CONEXION invalida");
            }
        }
        if (errores.size() > 0) {
            String mensajeError = "";
            for(String error: errores) {
                mensajeError += error + "; ";
            }
            throw new ReservaException(mensajeError);
        }
    }
}