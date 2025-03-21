package ppss.P05;

public class MatriculaAlumnoTestable extends MatriculaAlumno{
    Operacion operacion;
    @Override
    public Operacion getOperacion() {
        return operacion;
    }
    public void setOperacion(Operacion operacion){
        this.operacion = operacion;
    }
}
