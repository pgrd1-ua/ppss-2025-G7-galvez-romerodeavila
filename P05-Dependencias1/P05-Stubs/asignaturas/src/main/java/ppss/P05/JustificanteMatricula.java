package ppss.P05;

import java.util.ArrayList;

public class JustificanteMatricula {
    private String dni;
    private ArrayList asignaturas;
    private ArrayList errores;
    //getters y setters

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setAsignaturas(ArrayList asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void setErrores(ArrayList errores) {
        this.errores = errores;
    }

    public String getDni() {
        return dni;
    }

    public ArrayList getAsignaturas() {
        return asignaturas;
    }

    public ArrayList getErrores() {
        return errores;
    }
}
