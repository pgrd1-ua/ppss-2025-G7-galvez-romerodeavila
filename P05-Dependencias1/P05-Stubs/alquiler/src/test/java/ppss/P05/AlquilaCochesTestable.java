package ppss.P05;

import ppss.IService;

public class AlquilaCochesTestable extends AlquilaCoches{
    IService iservice;
    @Override
    public IService getService(){
        return iservice;
    }
    public void setIservice(IService iservice){
        this.iservice = iservice;
    }
    public void setCalendario(Calendario c){
        this.calendario = c;
    }
}
