package ppss.excepciones;

public class JDBCException extends Exception{
    public JDBCException(){
    }
    public JDBCException(String m){
        super(m);
    }
}
