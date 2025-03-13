package ppss.P05;

import java.util.ArrayList;

public class OperacionStub extends Operacion{
    @Override
    public void setAsignaturas(ArrayList<String> asignaturas) {
        super.setAsignaturas(asignaturas);
    }
    @Override
    public void setCursadas(ArrayList<String> cursadas){
        this.cursadas = cursadas;
    }
}
