package ppss.matriculacion.dao;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.*;
import ppss.matriculacion.to.AlumnoTO;

public class AlumnoDAOIT {
    private IAlumnoDAO alumnoDAO; //SUT
    private IDatabaseTester databaseTester; //
    private IDatabaseConnection connection;

    @BeforeEach
    public void setUp() throws Exception {

        String cadena_conexionDB = "jdbc:mysql://localhost:3306/DBUNIT";
        databaseTester = new MiJdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                cadena_conexionDB, "root", "ppss");
        connection = databaseTester.getConnection();
        alumnoDAO = new FactoriaDAO().getAlumnoDAO();
    }
    @Test
    public void testA1() throws Exception {
        AlumnoTO p = new AlumnoTO();
        p.setNif("33333333C");
        p.setNombre("Elena Aguirre Juarez");
        p.setFechaNacimiento(LocalDate.of(1985,02,22));

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        Assertions.assertDoesNotThrow(()-> alumnoDAO.addAlumno(p));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }
    @Test
    public void testA2() throws Exception {
        AlumnoTO p = new AlumnoTO();
        p.setNif("11111111A");
        p.setNombre("Alfonso Ramirez Ruiz");
        p.setFechaNacimiento(LocalDate.of(1982,02,22));

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException excepcionObtenida = Assertions.assertThrows(DAOException.class, ()-> alumnoDAO.addAlumno(p));

        Assertions.assertEquals("Error al conectar con BD", excepcionObtenida.getMessage());
    }
    @Test
    public void testA3() throws Exception {
        AlumnoTO p = new AlumnoTO();
        p.setNif("44444444D");
        p.setNombre(null);
        p.setFechaNacimiento(LocalDate.of(1982,02,22));

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException excepcionObtenida = Assertions.assertThrows(DAOException.class, ()-> alumnoDAO.addAlumno(p));

        Assertions.assertEquals("Error al conectar con BD", excepcionObtenida.getMessage());
    }
    @Test
    public void testA4() throws Exception {
        AlumnoTO p = new AlumnoTO();

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException excepcionObtenida = Assertions.assertThrows(DAOException.class, ()-> alumnoDAO.addAlumno(p));

        Assertions.assertEquals("Alumno nulo", excepcionObtenida.getMessage());
    }
    @Test
    public void testA5() throws Exception {
        AlumnoTO p = new AlumnoTO();
        p.setNif(null);
        p.setNombre("Pedro Garcia Lopez");
        p.setFechaNacimiento(LocalDate.of(1982,02,22));

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException excepcionObtenida = Assertions.assertThrows(DAOException.class, ()-> alumnoDAO.addAlumno(p));

        Assertions.assertEquals("Error al conectar con BD", excepcionObtenida.getMessage());
    }
    @Test
    public void testB1() throws Exception {
        String nif = "11111111A";

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Assertions.assertDoesNotThrow(()-> alumnoDAO.delAlumno(nif));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }
    @Test
    public void testB2() throws Exception {
        String nif = "33333333C";
        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException excepcionObtenida = Assertions.assertThrows(DAOException.class, ()-> alumnoDAO.delAlumno(nif));

        Assertions.assertEquals("No se ha borrado ningun alumno", excepcionObtenida.getMessage());
    }
}
