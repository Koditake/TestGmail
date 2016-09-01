/**
 * Created by thien on 8/30/2016.
 */
import com.source.Gmail;
import com.source.YahooMail;
import java.util.Scanner;
import javax.mail.MessagingException;


/**
 * Created by thien on 8/30/2016.
 */
public class Main {
    public static void main(String[] args) throws MessagingException{

        Scanner key = new Scanner(System.in);

       // System.out.print("sender: "); String user=key.next();
        // System.out.print("password: "); String password=key.next();
        System.out.print("receiver: "); String mailto=key.nextLine();
        System.out.print("subject: "); String subject=key.nextLine();
        //key.nextLine();
        System.out.print("message: "); String body=key.nextLine();
        Gmail.sendMail(mailto, body, subject);
        YahooMail.sendMail(mailto,body,subject);
        System.out.println("EOP...");
    }
}
