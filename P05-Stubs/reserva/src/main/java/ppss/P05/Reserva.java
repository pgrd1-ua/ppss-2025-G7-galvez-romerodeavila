package ppss.P05;

import ppss.IOperacionBO;
import ppss.excepciones.*;


import java.util.ArrayList;

//paquete ppss
public class Reserva {
    Operacion operacion;
    public Operacion getOperacion(){
        return operacion;
    }
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void realizaReserva(String login, String password, String socio, String[] isbns) throws ReservaException {
        ArrayList<String> errores = new ArrayList<>();
        if (!compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)) { //D.E
            errores.add("ERROR de permisos");
        }
        else {
            IOperacionBO io = Factoria.create(); //D.E
            try {
                for (String isbn : isbns) {
                    try {
                        io.operacionReserva(socio, isbn);//D.E
                    } catch (IsbnInvalidoException iie) {
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
            for (String error : errores) {
                mensajeError += error + "; ";
            }
            throw new ReservaException(mensajeError);
        }
    }
}