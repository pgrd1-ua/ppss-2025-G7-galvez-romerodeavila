package ppss.P06;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.easymock.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class NotifyCenterTest {

    NotifyCenter notifyCenter;
    MailServer mailServer;
    IMocksControl ctrl;
    @BeforeEach
    public void setUp(){
        ctrl = EasyMock.createStrictControl();
        notifyCenter = EasyMock.partialMockBuilder(NotifyCenter.class).addMockedMethod("sendNotify").addMockedMethod("getServer").addMockedMethod("getFechaActual").mock(ctrl);
        mailServer = EasyMock.partialMockBuilder(MailServer.class).withConstructor("root","7l65a43").addMockedMethod("findMailItemsWithDate").mock(ctrl); //new MailServer()
    }
    @Test
    public void A_notifyUsers_should_return_FailuresDuringSending_when_failEmail2Email3(){
        LocalDate fechaHoy = LocalDate.of(2025,3,11);
        LocalDate fechaEntrada = fechaHoy;
        String mensajeEsperado = "Failures during sending process";
        FailedNotifyException excepcionEsperada = new FailedNotifyException(mensajeEsperado);
        List<String> emailsRecibidos = Arrays.asList("email1", "email2", "email3", "email4");

        EasyMock.expect(notifyCenter.getServer()).andReturn(mailServer);
        EasyMock.expect(notifyCenter.getFechaActual()).andReturn(fechaHoy);
        EasyMock.expect(mailServer.findMailItemsWithDate(fechaEntrada)).andReturn(emailsRecibidos);

        assertDoesNotThrow(()-> {
            notifyCenter.sendNotify("email1");
            EasyMock.expectLastCall();
            notifyCenter.sendNotify("email2");
            EasyMock.expectLastCall().andThrow(new FailedNotifyException());
            notifyCenter.sendNotify("email3");
            EasyMock.expectLastCall().andThrow(new FailedNotifyException());
            notifyCenter.sendNotify("email4");
            EasyMock.expectLastCall();
        });
        ctrl.replay();
        FailedNotifyException failedNotifyException = assertThrows(FailedNotifyException.class, ()-> notifyCenter.notifyUsers(fechaEntrada));
        assertEquals(excepcionEsperada.getMessage(), failedNotifyException.getMessage());
        ctrl.verify();
    }
    @Test
    public void B_notifyUsers_should_return_DateError_when_datesDiferentes(){
        LocalDate fechaHoy = LocalDate.of(2025,2,12);
        LocalDate fechaEntrada = LocalDate.of(2025,3,12);
        String mensajeEsperado = "Date error";
        FailedNotifyException excepcionEsperada = new FailedNotifyException(mensajeEsperado);
        List<String> emailsRecibidos = Arrays.asList("email1", "email2", "email3", "email4");

        EasyMock.expect(notifyCenter.getServer()).andReturn(mailServer);
        EasyMock.expect(notifyCenter.getFechaActual()).andReturn(fechaHoy);

        ctrl.replay();
        FailedNotifyException failedNotifyException = assertThrows(FailedNotifyException.class, ()-> notifyCenter.notifyUsers(fechaEntrada));
        assertEquals(excepcionEsperada.getMessage(), failedNotifyException.getMessage());
        ctrl.verify();
    }
    @Test
    public void C_notifyUsers_should_return_nada_when_servidorSinMensajes(){
        LocalDate fechaHoy = LocalDate.of(2025,3,11);
        LocalDate fechaEntrada = fechaHoy;
        List<String> emailsRecibidos = new ArrayList<>();

        EasyMock.expect(notifyCenter.getServer()).andReturn(mailServer);
        EasyMock.expect(notifyCenter.getFechaActual()).andReturn(fechaHoy);
        EasyMock.expect(mailServer.findMailItemsWithDate(fechaEntrada)).andReturn(emailsRecibidos);

        ctrl.replay();
        assertDoesNotThrow(()-> notifyCenter.notifyUsers(fechaEntrada));
        ctrl.verify();
    }
}
