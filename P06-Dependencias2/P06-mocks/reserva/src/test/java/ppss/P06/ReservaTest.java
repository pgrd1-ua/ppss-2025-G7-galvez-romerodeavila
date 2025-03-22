package ppss.P06;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.IOperacionBO;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {
    IMocksControl ctrl;
    Reserva reservaMock;
    FactoriaBOs factoriaBOs;
    IOperacionBO iOperacionBO;
    String [] isbns = {"11111","22222", "33333","44444", "55555"};
    String loginOK = "ppss";
    String passwordOK = "ppss";
    String socioOK = "Pepe";
    Usuario tipoUsuario = Usuario.BIBLIOTECARIO;
    @BeforeEach
    public void setUp(){
        ctrl = EasyMock.createStrictControl();
        reservaMock = EasyMock.partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").mock(ctrl);
        factoriaBOs = ctrl.mock(FactoriaBOs.class);
        iOperacionBO = ctrl.mock(IOperacionBO.class);
    }
    @Test
    public void C1_realizaReserva_should_return_ErrorPermisos_when_loginnotOK_and_passwnotOK(){
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String [] isbn = {"33333"};
        ReservaException exceptionEsperada = new ReservaException("ERROR de permisos; ");
        EasyMock.expect(reservaMock.compruebaPermisos(login, password, tipoUsuario)).andReturn(false);

        ctrl.replay();
        ReservaException exceptionObtenida = assertThrows(ReservaException.class, () -> reservaMock.realizaReserva(login,password,socio,isbn));
        assertEquals(exceptionEsperada.getMessage(), exceptionObtenida.getMessage());
        ctrl.verify();
    }
    @Test
    public void C2_realizaReserva_should_return_notExc_when_allOK(){
        String [] isbnsEntrada = {"22222", "33333"};
        assertDoesNotThrow(()-> EasyMock.expect(reservaMock.compruebaPermisos(loginOK, passwordOK, tipoUsuario)).andReturn(true));
        EasyMock.expect(factoriaBOs.getOperacionBO()).andReturn(iOperacionBO);
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socioOK, isbns[1]));
        EasyMock.expectLastCall();
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socioOK, isbns[2]));
        EasyMock.expectLastCall();
        ctrl.replay();
        reservaMock.setFactoriaBOs(factoriaBOs);
        assertDoesNotThrow(() -> reservaMock.realizaReserva(loginOK,passwordOK,socioOK,isbnsEntrada));
        ctrl.verify();
    }
    @Test
    public void C3_realizaReserva_should_return_ISBNExcep_when_isbns1111155555(){
        ReservaException exceptionEsperada = new ReservaException("ISBN invalido:11111; ISBN invalido:55555; ");
        String [] isbnsEntrada = {"11111","22222", "55555"};
        assertDoesNotThrow(()-> EasyMock.expect(reservaMock.compruebaPermisos(loginOK, passwordOK, tipoUsuario)).andReturn(true));
        EasyMock.expect(factoriaBOs.getOperacionBO()).andReturn(iOperacionBO);
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socioOK, isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException()); //es void
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socioOK, isbns[1]));
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socioOK, isbns[4]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        ctrl.replay();
        reservaMock.setFactoriaBOs(factoriaBOs);
        ReservaException exceptionObtenida = assertThrows(ReservaException.class, () -> reservaMock.realizaReserva(loginOK,passwordOK,socioOK,isbnsEntrada));
        assertEquals(exceptionEsperada.getMessage(), exceptionObtenida.getMessage());
        ctrl.verify();
    }
    @Test
    public void C4_realizaReserva_should_return_SocioExcep_when_Luis(){
        ReservaException exceptionEsperada = new ReservaException("SOCIO invalido; ");
        String [] isbnsEntrada = {"22222"};
        String socionotOk= "Luis";
        assertDoesNotThrow(()-> EasyMock.expect(reservaMock.compruebaPermisos(loginOK, passwordOK, tipoUsuario)).andReturn(true));
        EasyMock.expect(factoriaBOs.getOperacionBO()).andReturn(iOperacionBO);
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socionotOk, isbns[1]));
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException()); //es void
        ctrl.replay();
        reservaMock.setFactoriaBOs(factoriaBOs);
        ReservaException exceptionObtenida = assertThrows(ReservaException.class, () -> reservaMock.realizaReserva(loginOK,passwordOK,socionotOk,isbnsEntrada));
        assertEquals(exceptionEsperada.getMessage(), exceptionObtenida.getMessage());
        ctrl.verify();
    }
    @Test
    public void C5_realizaReserva_should_return_ISBNExcep_and_JDBCExcep_when_isbns11111_and_conexInvalida(){
        ReservaException exceptionEsperada = new ReservaException("ISBN invalido:11111; CONEXION invalida; ");
        String [] isbnsEntrada = {"11111","22222", "33333"};
        assertDoesNotThrow(()-> EasyMock.expect(reservaMock.compruebaPermisos(loginOK, passwordOK, tipoUsuario)).andReturn(true));
        EasyMock.expect(factoriaBOs.getOperacionBO()).andReturn(iOperacionBO);
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socioOK, isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException()); //es void
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socioOK, isbns[1]));
        assertDoesNotThrow(()-> iOperacionBO.operacionReserva(socioOK, isbns[2]));
        EasyMock.expectLastCall().andThrow(new JDBCException());
        ctrl.replay();
        reservaMock.setFactoriaBOs(factoriaBOs);
        ReservaException exceptionObtenida = assertThrows(ReservaException.class, () -> reservaMock.realizaReserva(loginOK,passwordOK,socioOK,isbnsEntrada));
        assertEquals(exceptionEsperada.getMessage(), exceptionObtenida.getMessage());
        ctrl.verify();
    }
}
