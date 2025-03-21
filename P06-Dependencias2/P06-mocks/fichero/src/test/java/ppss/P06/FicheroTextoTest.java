package ppss.P06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.easymock.*;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FicheroTextoTest {
    FicheroTexto ficheroTexto;
    FileReader fileReader;
    IMocksControl ctrl;
    @BeforeEach
    public void setUp(){
        ctrl = EasyMock.createStrictControl();
        ficheroTexto = EasyMock.partialMockBuilder(FicheroTexto.class).addMockedMethod("getFileReader").mock(ctrl);
        fileReader = ctrl.mock(FileReader.class);
    }
    @Test
    void C1_contarCaracteres_should_return_ErrorLectura(){
        String nombreFichero = "src/test/resources/ficheroC1.txt";
        String mensajeExcepcion = nombreFichero + " (Error al leer el archivo)";

        assertDoesNotThrow(()-> EasyMock.expect(ficheroTexto.getFileReader(nombreFichero)).andReturn(fileReader));
        assertDoesNotThrow(()-> EasyMock.expect(fileReader.read()).andReturn((int)'a').andReturn((int) 'b').andThrow(new IOException()));
        //
        ctrl.replay();
        FicheroException ficheroException = assertThrows(FicheroException.class, () -> ficheroTexto.contarCaracteres(nombreFichero));
        assertEquals(mensajeExcepcion, ficheroException.getMessage());
        ctrl.verify();
    }
    @Test
    void C2_contarCaracteres_should_return_ErrorCerrar(){
        String nombreFichero = "src/test/resources/ficheroC2.txt";
        String mensajeExcepcion = nombreFichero + " (Error al cerrar el archivo)";

        assertDoesNotThrow(()-> EasyMock.expect(ficheroTexto.getFileReader(nombreFichero)).andReturn(fileReader));
        assertDoesNotThrow(()-> EasyMock.expect(fileReader.read()).andReturn((int)'a').andReturn((int) 'b').andReturn((int) 'c').andReturn(-1));
        assertDoesNotThrow(()-> fileReader.close());// NO SE ESPERA NADA
        EasyMock.expectLastCall().andThrow(new IOException());
        //
        ctrl.replay();
        FicheroException ficheroException = assertThrows(FicheroException.class, ()-> ficheroTexto.contarCaracteres(nombreFichero));
        assertEquals(mensajeExcepcion, ficheroException.getMessage());
        ctrl.verify();
    }
}
