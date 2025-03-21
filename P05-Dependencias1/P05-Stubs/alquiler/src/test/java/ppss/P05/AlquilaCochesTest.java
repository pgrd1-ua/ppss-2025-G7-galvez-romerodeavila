package ppss.P05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.MensajeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AlquilaCochesTest {
    AlquilaCochesTestable alquilaCochesTestable;
    CalendarioStub calendarioStub;
    ServicioStub servicioStub;
    Ticket ticketEsperado;
    TipoCoche tipoCoche;
    LocalDate fechaInicio;
    int dias;

    @BeforeEach
    public void setUp(){
        alquilaCochesTestable = new AlquilaCochesTestable();
        calendarioStub = new CalendarioStub();
        servicioStub = new ServicioStub();
        ticketEsperado = new Ticket();

    }
    @Test
    void C1_calculaPrecio_should_return_ticket75_when_turismo_nofest(){
        tipoCoche = TipoCoche.TURISMO;
        fechaInicio = LocalDate.of(2024,05,18);
        dias = 10;
        float precioBase = 10;

        servicioStub.setPrecio(precioBase);
        calendarioStub.setDiaFest(new ArrayList<>());
        calendarioStub.setException(new ArrayList<>());
        alquilaCochesTestable.setCalendario(calendarioStub);
        alquilaCochesTestable.setIservice(servicioStub);
        ticketEsperado.setPrecio_final(75);

        Ticket ticketObtenido = assertDoesNotThrow(()->alquilaCochesTestable.calculaPrecio(tipoCoche,fechaInicio, dias));
        assertEquals(ticketEsperado.getPrecio_final(), ticketObtenido.getPrecio_final());
    }
    @Test
    void C2_calculaPrecio_should_return_ticket62con5_when_caravana_solo20y24(){
        tipoCoche = TipoCoche.TURISMO;
        fechaInicio = LocalDate.of(2024,06,19);
        dias = 7;
        float precioBase = 10;

        servicioStub.setPrecio(precioBase);
        calendarioStub.setDiaFest(Arrays.asList(20, 24));
        calendarioStub.setException(new ArrayList<>());
        alquilaCochesTestable.setCalendario(calendarioStub);
        alquilaCochesTestable.setIservice(servicioStub);
        ticketEsperado.setPrecio_final(62.5f);

        Ticket ticketObtenido = assertDoesNotThrow(()->alquilaCochesTestable.calculaPrecio(tipoCoche,fechaInicio, dias));
        assertEquals(ticketEsperado.getPrecio_final(), ticketObtenido.getPrecio_final());
    }
    @Test
    void C3_calculaPrecio_should_return_3Exception_when_turismo_excp182122(){
        tipoCoche = TipoCoche.TURISMO;
        fechaInicio = LocalDate.of(2024,04,17);
        dias = 8;
        float precioBase = 10;

        servicioStub.setPrecio(precioBase);
        calendarioStub.setDiaFest(new ArrayList<>());
        calendarioStub.setException(Arrays.asList(18,21,22));
        alquilaCochesTestable.setCalendario(calendarioStub);
        alquilaCochesTestable.setIservice(servicioStub);

        MensajeException resultado = assertThrows(MensajeException.class,()->alquilaCochesTestable.calculaPrecio(TipoCoche.TURISMO,fechaInicio,dias));
        assertEquals("Error en dia: 2024-04-18; Error en dia: 2024-04-21; Error en dia: 2024-04-22; ", resultado.getMessage());
    }

}
