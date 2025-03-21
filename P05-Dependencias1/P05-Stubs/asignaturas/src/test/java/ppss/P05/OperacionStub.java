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
    public void compruebaMatricula(String dni, String asignatura) throws AsignaturaIncorrectaException, AsignaturaCursadaException{
        boolean cursada = false;
        boolean existe = false;
        for (String a: asignaturas) {
            if(Objects.equals(a, asignatura)){
                existe = true;
                break;
            }
        }
        for (String c : cursadas){
            if (Objects.equals(c, asignatura)){
                cursada = true;
                break;
            }
        }
        if (!existe){
            throw new AsignaturaIncorrectaException();
        }
        if (cursada){
            throw new AsignaturaCursadaException();
        }
    }
}
