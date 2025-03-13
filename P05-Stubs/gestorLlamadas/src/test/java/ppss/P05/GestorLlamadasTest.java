package ppss.P05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLlamadasTest {

    GestorLlamadasTestable gestorLlamadasTestable;
    CalendarioStub calendarioStub;
    @BeforeEach
    void setUp(){
        calendarioStub = new CalendarioStub();
        gestorLlamadasTestable = new GestorLlamadasTestable();
    }
    @Test
    void C1_GestorLlamadas_should_return_207_when_10min_12hora(){
        int hora= 12;
        int minutos= 10;
        double resultadoEsperado = 207;
        calendarioStub.setHora(hora);
        gestorLlamadasTestable.setGestorLlamadas(calendarioStub);
        double resultadoObtenido = gestorLlamadasTestable.calculaConsumo(minutos);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    @Test
    void C2_GestorLlamadas_should_return_122_when_10min_21hora(){
        int hora= 21;
        int minutos= 10;
        double resultadoEsperado = 122;
        calendarioStub.setHora(hora);
        gestorLlamadasTestable.setGestorLlamadas(calendarioStub);
        double resultadoObtenido = gestorLlamadasTestable.calculaConsumo(minutos);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
}
