package ppss.practica3;

public class Cine {
    public boolean[] asientos;
    public boolean reservaButacas(boolean[] asientos, int solicitados) throws ButacasException {
        boolean reserva = false;
        int j=0;
        int sitiosLibres =0;
        int primerLibre;

        if(solicitados < 0){
            throw new ButacasException("No se puede procesar la solicitud");
        }
        if(solicitados <= asientos.length) {
            while ((j < asientos.length) && (sitiosLibres < solicitados)) {
                if (!asientos[j]) {
                    sitiosLibres++;
                } else {
                    sitiosLibres = 0;
                }
                j++;
            }
            if (sitiosLibres == solicitados) {
                primerLibre = (j - solicitados);
                reserva = true;
                for (int k = primerLibre; k <= (primerLibre + solicitados); k++) {
                    asientos[k] = true;
                }
            }
        }
        else {
            throw new ButacasException("No se puede procesar la solicitud");
        }
        return reserva;
    }
}