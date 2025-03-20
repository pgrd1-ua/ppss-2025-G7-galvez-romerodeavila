package ppss.P05;

import ppss.excepciones.CalendarioException;

import java.time.LocalDate;
import java.util.List;

public class CalendarioStub extends Calendario{
    List<Integer> diafest;
    List<Integer> exception;

    public void setDiaFest(List<Integer> diaFest){
        this.diafest = diafest;
    }

    public void setException(List<Integer> exception) {
        this.exception = exception;
    }
    public CalendarioStub(List<Integer> diafest, List<Integer> exception){
        this.diafest = diafest;
        this.exception = exception;
    }

    @Override
    public boolean es_festivo(LocalDate otroDia) throws CalendarioException {
        if (diafest.contains(otroDia.getDayOfMonth())) {
            return true;
        } else if (exception.contains(otroDia.getDayOfMonth())) {
            throw new CalendarioException("Error en dia: " + otroDia + ";");
        } else
            return false;

    }
}
