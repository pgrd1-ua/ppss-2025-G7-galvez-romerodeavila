package ppss.P05;

import ppss.IOperacionBO;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.Objects;

public class OperacionStub implements IOperacionBO {
    boolean throwIsbn = false, throwJDBC = false, throwSocioInvalido = false;
    String [] isbnBaseDatos = {"11111", "22222"};
    public void setThrowIsbn(boolean throwIsbn){ this.throwIsbn = throwIsbn; }
    public void setThrowJDBC(boolean throwJDBC) { this.throwJDBC = throwJDBC; }
    public void setThrowSocioInvalido(boolean throwSocioInvalido) { this.throwSocioInvalido = throwSocioInvalido; }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if(throwIsbn){
            boolean existe = false;
            for(String i : isbnBaseDatos){
                if (Objects.equals(i, isbn)) {
                    existe = true;
                    break;
                }
            }
            if(!existe){
                throw new IsbnInvalidoException();
            }
        }
        if(throwJDBC){ throw new JDBCException(); }
        if(throwSocioInvalido){ throw new SocioInvalidoException(); }
    }
}
