package ppss.P06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.easymock.*;

import static org.junit.jupiter.api.Assertions.*;

public class PremioTest {
    Premio premio;
    ClienteWebService clienteWebService;
    IMocksControl ctrl;
    @BeforeEach
    public void setUp(){
        ctrl = EasyMock.createStrictControl();
        premio = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);
        clienteWebService = ctrl.mock(ClienteWebService.class);

    }
    @Test
    public void A_compruebaPremio_should_return_finalChampions_when_num007(){
        float  numGenerado = 0.07f;
        String resultadoObtenerPremio = "entrada final Champions";
        String resultadoEsperado= "Premiado con entrada final Champions";
        // expectativas
        EasyMock.expect(premio.generaNumero()).andReturn(numGenerado);
        assertDoesNotThrow(()-> EasyMock.expect(clienteWebService.obtenerPremio()).andReturn(resultadoObtenerPremio));
        // replay
        ctrl.replay();
        //Inyectamos el doble
        premio.cliente = clienteWebService;
        // Invocar a la sut
        String resultadoObtenido = premio.compruebaPremio();
        // equals
        assertEquals(resultadoEsperado, resultadoObtenido);
        // verify
        ctrl.verify();
    }
    @Test
    public void B_compruebaPremio_should_return_NoPremioExcep_when_num005(){
        float  numGenerado = 0.05f;
        String resultadoEsperado= "No se ha podido obtener el premio";
        // expectativas
        EasyMock.expect(premio.generaNumero()).andReturn(numGenerado);
        assertDoesNotThrow(()->EasyMock.expect(clienteWebService.obtenerPremio()).andThrow(new ClienteWebServiceException()));
        // replay
        ctrl.replay();
        //Inyectamos el doble
        premio.cliente = clienteWebService;
        // Invocar a la sut
        String resultadoObtenido = premio.compruebaPremio();
        // equals
        assertEquals(resultadoEsperado, resultadoObtenido);
        // verify
        ctrl.verify();
    }
    @Test
    public void C_compruebaPremio_should_return_SinPremio_when_num048(){
        float  numGenerado = 0.48f;
        String resultadoObtenerPremio = "entrada final Champions";
        String resultadoEsperado= "Sin premio";
        // expectativas
        EasyMock.expect(premio.generaNumero()).andReturn(numGenerado);
        // replay
        ctrl.replay();
        //Inyectamos el doble
        premio.cliente = clienteWebService;
        // Invocar a la sut
        String resultadoObtenido = premio.compruebaPremio();
        // equals
        assertEquals(resultadoEsperado, resultadoObtenido);
        // verify
        ctrl.verify();
    }
}
