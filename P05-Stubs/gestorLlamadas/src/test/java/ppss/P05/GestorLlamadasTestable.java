package ppss.P05;

public class GestorLlamadasTestable extends GestorLlamadas{
    Calendario calendario;
    public void setGestorLlamadas(Calendario calendario){
        this.calendario = calendario;
    }
    @Override
    public Calendario getCalendario() {
        return this.calendario;
    }
}
