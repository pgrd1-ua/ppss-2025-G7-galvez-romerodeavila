package ppss.P06;

public class FailedNotifyException extends Exception{
    String mensaje;
    public FailedNotifyException(){}
    public FailedNotifyException(String mensaje){
       super(mensaje);
    }
}
