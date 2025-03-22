package ppss.P05;

import java.util.ArrayList;
import java.util.Objects;

public class OperacionStub extends Operacion{
    ArrayList<String> asignaturas;
    ArrayList<String> cursadas;

    public void setAsignaturas(ArrayList<String> asignaturas) {
        this.asignaturas = asignaturas;
    }
    public void setCursadas(ArrayList<String> cursadas) {
        this.cursadas = cursadas;
    }
    @Override
    public void compruebaMatricula(String dni, String asignatura) throws AsignaturaIncorrectaException, AsignaturaCursadaException {
        if (!asignaturas.contains(asignatura)){
            throw new AsignaturaIncorrectaException();
        }
        if (cursadas.contains(asignatura)){
            throw new AsignaturaCursadaException();
        }
    }
}
