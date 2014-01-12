package upmc.aar2013.project.heraclessport.server.tools;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel;

/**
 * ServerMail permet d'envoyer des mails informant les utilisateurs des résultats
 * de leurs paris.
 */
public class ServerMail {

	public void sendResult(ParisModel paris) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String htmlBody = "<div style='background-color:#000000; color:#FFFFFF; padding:10px; font-size:16px;'>";
        htmlBody += "<h1 style='text-align:center; color:#FF6600;'>Bonjour "+paris.getParis_user().getUser_pseudo()+"<br/>Heracles-Sport vous informe que votre paris est terminé</h1>";
        if(paris.getResult()>0) {
        	htmlBody += "<h2 style='text-align:center; color:#33FF33;'>Paris gagné (+"+paris.getResult()+")</h2>";
        }
        else {
        	htmlBody += "<h2 style='text-align:center; color:#FF3333;'>Paris perdu. ("+paris.getResult()+")</h2>";
        }
        htmlBody += "<p>";
        htmlBody += "<b>Sport:</b> <span style='color:#FF6600;'>"+paris.getParis_sched().getSched_sportName()+"</span><br/>";
        htmlBody += "<b>Date: </b> <span style='color:#FF6600;'>"+paris.getParis_sched().getSched_dateClean()+"</span><br/>";
        if(paris.getParis_sched() instanceof ScheduleTeamModel) {
        	 htmlBody += "<b>Equipe Domicile: </b> <span style='color:#FF6600;'>"+((ScheduleTeamModel)paris.getParis_sched()).getSched_home_team().getTeam_name()+"</span><br/>";
        	 htmlBody += "<b>Equipe Extérieur: </b> <span style='color:#FF6600;'>"+((ScheduleTeamModel)paris.getParis_sched()).getSched_away_team().getTeam_name()+"</span><br/>";
        }
        htmlBody += "</p>";
        htmlBody += "<p><br/><center><a style='font-size:16px;font-weight:bold;color:#FF6600;' href='http://heracles-sport.appspot.com/histo'>Pour plus de détails sur vos paris terminés, cliquez ici.</a></center><br/></p>";
        htmlBody += "</div>";
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("clement.bar@gmail.com", "heracles-sport.appspot.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(paris.getParis_user().getUser_mail(), paris.getParis_user().getUser_name()));
            msg.setSubject("[Heracles-Sport] - Resultats paris");
            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html; charset=utf-8");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);
            Transport.send(msg);

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
