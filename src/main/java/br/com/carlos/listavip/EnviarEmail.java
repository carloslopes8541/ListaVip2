package br.com.carlos.listavip;

import br.com.carlos.listavip.entity.Convidado;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EnviarEmail {

    public void enviar(Convidado convidado) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("carloseduardopereira200@gmail.com", "85419480"));
            email.setSSLOnConnect(true);

            email.setFrom("carloseduardopereira200@gmail.com");
            email.setSubject("Você foi convidado pelo ListaVIP");
            email.setMsg("Olá " + convidado.getNome() + ". Você acaba de ser convidado pelo ListaVIP.");
            email.addTo(convidado.getEmail());
            email.send();

        } catch (EmailException  e) {
            e.printStackTrace();
        }
    }

}
