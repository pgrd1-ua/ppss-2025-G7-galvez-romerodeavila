package ppss.P05;

import java.util.ArrayList;

public class MatriculaAlumno {

    protected Operacion getOperacion() {
        return new Operacion();
    }
    public JustificanteMatricula validaAsignaturas(String dni, String[] asignaturas) {
        JustificanteMatricula justificante = new JustificanteMatricula();
        ArrayList validas = new ArrayList<>();
        ArrayList listaErrores = new ArrayList<>();
        Operacion op = getOperacion(); //D.E
        for (String asignatura: asignaturas) {
            try {
                op.compruebaMatricula(dni, asignatura);//D.E
                validas.add(asignatura);
            }
            catch (AsignaturaIncorrectaException ex) {
                listaErrores.add("Asignatura " + asignatura + " no existe");
            }
            catch (AsignaturaCursadaException ex) {
                listaErrores.add("Asignatura " + asignatura + " ya cursada");
            }
        }
        justificante.setDni(dni);
        justificante.setAsignaturas(validas);
        justificante.setErrores(listaErrores);
        return justificante;
    }
}
