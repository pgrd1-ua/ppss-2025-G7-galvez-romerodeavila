package ppss.P05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {
    ReservaTestable reservaTestable;
    OperacionStub operacionStub;
    Usuario user;

    String loginOK = "ppss";
    String passwordOK = "ppss";
    String socioOK = "Luis";
    Usuario userOK = Usuario.BIBLIOTECARIO;
    String [] isbnsGeneral = {"11111", "22222", "33333", "44444"};

    @BeforeEach
    public void setUp(){
        reservaTestable = new ReservaTestable();
        operacionStub = new OperacionStub();
        reservaTestable.setLoginOK(loginOK);
        reservaTestable.setPasswordOK(passwordOK);
        reservaTestable.setTipoUsuOK(userOK);
    }
    @Test
    public void C1_realizaReserva_should_return_ErrorPermisos_when_login_and_pass_xxxx(){
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Luis";
        String[] isbns = {isbnsGeneral[0]};
        user = Usuario.BIBLIOTECARIO;
        ReservaException reservaExceptionEsperada = new ReservaException("ERROR de permisos; ");
        ReservaException reservaExceptionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login,password,socio, isbns));
        assertEquals(reservaExceptionEsperada.getMessage(), reservaExceptionObtenida.getMessage());
    }

    @Test
    public void C2_realizaReserva_should_return_NoException_when_reserva_is_completed(){
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = {isbnsGeneral[0], isbnsGeneral[1]};

        Factoria.setOperacion(operacionStub);
        assertDoesNotThrow(() -> reservaTestable.realizaReserva(login,password,socio, isbns));
    }
    @Test
    public void C3_realizaReserva_should_return_IsbnExcep_when_ids_noregistrados(){
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";

        String[] isbns = {isbnsGeneral[0], isbnsGeneral[2], isbnsGeneral[3]};

        operacionStub.setThrowIsbn(true);
        Factoria.setOperacion(operacionStub);
        ReservaException reservaExceptionEsperada = new ReservaException("ISBN invalido:33333; ISBN invalido:44444; ");
        ReservaException reservaExceptionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login,password,socio, isbns));
        assertEquals(reservaExceptionEsperada.getMessage(), reservaExceptionObtenida.getMessage());
    }

    @Test
    public void C4_realizaReserva_should_return_SocioExcep_when_socio_invalido(){
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";

        String[] isbns = {isbnsGeneral[0]};

        operacionStub.setThrowSocioInvalido(true);
        Factoria.setOperacion(operacionStub);
        ReservaException reservaExceptionEsperada = new ReservaException("SOCIO invalido; ");
        ReservaException reservaExceptionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login,password,socio, isbns));
        assertEquals(reservaExceptionEsperada.getMessage(), reservaExceptionObtenida.getMessage());
    }

    @Test
    public void C5_realizaReserva_should_return_JDBCExcep_when_conexion_invalida(){
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";

        String[] isbns = {isbnsGeneral[0], isbnsGeneral[1]};

        operacionStub.setThrowJDBC(true);
        Factoria.setOperacion(operacionStub);
        ReservaException reservaExceptionEsperada = new ReservaException("CONEXION invalida; ");
        ReservaException reservaExceptionObtenida = assertThrows(ReservaException.class, () -> reservaTestable.realizaReserva(login,password,socio, isbns));
        assertEquals(reservaExceptionEsperada.getMessage(), reservaExceptionObtenida.getMessage());
    }
}
