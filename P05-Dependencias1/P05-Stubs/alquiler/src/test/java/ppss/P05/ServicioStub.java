package ppss.P05;

import ppss.IService;

public class ServicioStub implements IService {

    float precio;
    public void setPrecio(float precio){
        this.precio = precio;
    }
    @Override
    public float consultaPrecio(TipoCoche tipoCoche) {
        return precio;
    }
}
