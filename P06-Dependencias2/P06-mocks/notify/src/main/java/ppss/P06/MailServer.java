package ppss.P06;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MailServer {
    String[] emails = {"email1", "email2", "email3", "email4"};
    List<String> emailsRecibidos = Arrays.asList(emails);
    String login;
    String password;
    public MailServer(String login, String password){
        this.login = login;
        this.password = password;
    }
    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }
    public List<String> findMailItemsWithDate(LocalDate fecha){
        return emailsRecibidos;
    }
}
