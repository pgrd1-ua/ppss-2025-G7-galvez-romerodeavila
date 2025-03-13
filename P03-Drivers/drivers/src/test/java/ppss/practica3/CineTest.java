package ppss.practica3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import ppss.practica3.Cine.*;

public class CineTest {
    //@BeforeAll
    public Cine cine;

    @BeforeEach
    void setUp(){
        //Cine cine = new Cine();
        this.cine = new Cine();
    }

    @Test
    void C1_reservaButacas_should_return_ButacasException_when_fila_empty_and_want_3() {
        // Arrange : Codigo relativo a la preparacion de los datos de entrada
        boolean[] asientos = {};
        int solicitados = 3;
        String mensajeEsperado = "No se puede procesar la solicitud";

        // Act : ejecutamos la unidad a probar
        ButacasException exception = assertThrows(ButacasException.class, () -> cine.reservaButacas(asientos, solicitados));

        // Assert : Verificamos que el resultado real coincide con el esperado y emitimos el informe
        assertEquals(mensajeEsperado,exception.getMessage());
    }
    /*
    @Test
    void C2_reservaButacas_should_return_ButacasException_when_fila_empty_and_want_0(){
        boolean[] asientos = new boolean[0];
        int solicitados = 0;
        String mensajeEsperado = "No se puede procesar la solicitud";

        ButacasException exception = assertThrows(ButacasException.class, () -> cine.reservaButacas(asientos, solicitados));

        assertEquals(mensajeEsperado,exception.getMessage());
    }
    */
    @Test
    void C2_reservaButacas_should_return_false_when_solicitados_0(){
        boolean[] asientos = {};
        int solicitados = 0;
        boolean resultadoEsperado = false;
        boolean resultadoObtenido = assertDoesNotThrow(() ->cine.reservaButacas(asientos, solicitados));
        /*assertAll("C2_reservaBUtacas_should_return_false_when_solicitados_0",
                () -> assertEquals(resultadoEsperado,resultadoObtenido),
                () -> assertEquals(asientos, cine.asientos)
        );*/
        assertEquals(resultadoEsperado,resultadoObtenido);
    }

    @Test
    void C3_reservaButacas_should_return_true_when_solicitados_2(){
        boolean[] asientos = {false,false,false,true,true};
        int solicitados = 2;
        boolean resultadoEsperado = true;
        boolean resultadoObtenido = assertDoesNotThrow(()->cine.reservaButacas(asientos, solicitados));
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    void C4_reservaButacas_should_return_false_when_solicito_1_and_no_hay_asientos(){
        boolean[] asientos = {true, true, true};
        int solicitados = 1;
        boolean resultadoEsperado = false;
        boolean resultadoObtenido = assertDoesNotThrow(()-> cine.reservaButacas(asientos, solicitados));
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
}
