package ppss.P05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaAlumnoTest {
    MatriculaAlumnoTestable matriculaAlumnoTestable;
    OperacionStub operacionStub;
    JustificanteMatricula justificanteMatriculaEsperado;

    @BeforeEach
    public void setUp(){
        matriculaAlumnoTestable = new MatriculaAlumnoTestable();
        operacionStub = new OperacionStub();
        justificanteMatriculaEsperado = new JustificanteMatricula();
    }
    @Test
    public void C1_validaAsignaturas_should_return_ZZ_noexiste_and_P1_cursada(){
        String dni = "00000000T";
        String[] asignaturasSolicitadas = {"MD", "ZZ", "FBD", "P1"};

        ArrayList<String> listaErroresEsperados = new ArrayList<>(Arrays.asList("Asignatura ZZ no existe","Asignatura P1 ya cursada"));
        ArrayList<String> asignaturasTotales = new ArrayList<>(Arrays.asList("MD","FBD","P1"));
        ArrayList<String> asignaturasCursadas = new ArrayList<>(Arrays.asList("P1"));
        ArrayList<String> asignaturasMatriculadas = new ArrayList<>(Arrays.asList("MD","FBD"));

        operacionStub.setAsignaturas(asignaturasTotales);
        operacionStub.setCursadas(asignaturasCursadas);
        matriculaAlumnoTestable.setOperacion(operacionStub);

        justificanteMatriculaEsperado.setAsignaturas(asignaturasMatriculadas);
        justificanteMatriculaEsperado.setDni(dni);
        justificanteMatriculaEsperado.setErrores(listaErroresEsperados);

        JustificanteMatricula justificanteMatriculaObtenido = matriculaAlumnoTestable.validaAsignaturas(dni, asignaturasSolicitadas);
        assertAll(
                () -> assertEquals(justificanteMatriculaEsperado.getDni(), justificanteMatriculaObtenido.getDni()),
                () -> assertEquals(justificanteMatriculaEsperado.getAsignaturas(), justificanteMatriculaObtenido.getAsignaturas()),
                () -> assertEquals(justificanteMatriculaEsperado.getErrores(), justificanteMatriculaObtenido.getErrores())
        );
    }
    @Test
    public void C2_validaAsignaturas_should_return_matriculado_en_todas(){
        String dni = "00000000T";
        String[] asignaturasSolicitadas = {"PPSS", "ADA", "P3"};
        ArrayList<String> listaErroresEsperados = new ArrayList<>();
        ArrayList<String> asignaturasTotales = new ArrayList<>(Arrays.asList("PPSS","ADA","P3"));
        ArrayList<String> asignaturasCursadas = new ArrayList<>();
        ArrayList<String> asignaturasMatriculadas = new ArrayList<>(Arrays.asList("PPSS","ADA","P3"));

        operacionStub.setAsignaturas(asignaturasTotales);
        operacionStub.setCursadas(asignaturasCursadas);
        matriculaAlumnoTestable.setOperacion(operacionStub);

        justificanteMatriculaEsperado.setAsignaturas(asignaturasMatriculadas);
        justificanteMatriculaEsperado.setDni(dni);
        justificanteMatriculaEsperado.setErrores(listaErroresEsperados);

        JustificanteMatricula justificanteMatriculaObtenido = matriculaAlumnoTestable.validaAsignaturas(dni, asignaturasSolicitadas);
        assertAll(
                () -> assertEquals(justificanteMatriculaEsperado.getDni(), justificanteMatriculaObtenido.getDni()),
                () -> assertEquals(justificanteMatriculaEsperado.getAsignaturas(), justificanteMatriculaObtenido.getAsignaturas()),
                () -> assertEquals(justificanteMatriculaEsperado.getErrores(), justificanteMatriculaObtenido.getErrores())
        );
    }


}
