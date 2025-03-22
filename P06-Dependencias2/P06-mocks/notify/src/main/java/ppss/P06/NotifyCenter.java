package ppss.P06;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class NotifyCenter {
    private String login = "root";
    private String password = "7l65a43";
    public MailServer getServer() {
        return new MailServer(login, password);
    }
    public void sendNotify(String email) throws FailedNotifyException{
        throw new UnsupportedOperationException("Not supported yet");
    }
    public LocalDate getFechaActual(){
        LocalDate today = LocalDate.now();
        return today;
    }
    public void notifyUsers(LocalDate fecha) throws FailedNotifyException {
        int failed = 0;
        MailServer server = getServer(); //D.E de la clase de tipo MailServer
        List<String> emails;
        //LocalDate today = LocalDate.now(); //D.E de tipo LocalDate
        LocalDate today = getFechaActual();
        if (today.isEqual(fecha)) {
            emails = server.findMailItemsWithDate(fecha); //D.E de MailServer
            for (String email : emails) {
                try {
                    sendNotify(email); //D.E de la clase local
                }
                catch (FailedNotifyException ex) {
                    failed++;
                }
            }
        }
        else {
            throw new FailedNotifyException("Date error");
        }
        if (failed >0) {
            throw new FailedNotifyException("Failures during sending process");
        }
    }
}
