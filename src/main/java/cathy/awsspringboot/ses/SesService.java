package cathy.awsspringboot.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SesService {
    private final AmazonSimpleEmailService amazonSimpleEmailService;
    static final String FROM = "sender@example.com";
    static final String TO = "recipient@example.com";
    static final String CONFIGSET = "ConfigSet";
    static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";
    static final String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
            + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
            + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>"
            + "AWS SDK for Java</a>";
    static final String TEXTBODY = "This email was sent through Amazon SES "
            + "using the AWS SDK for Java.";
    public void sendEmail() {
        SendEmailResult response;

        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(TO))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(HTMLBODY))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM)
                    .withConfigurationSetName(CONFIGSET);

            response = amazonSimpleEmailService.sendEmail(request);
            System.out.println("Email sent. Message ID: " + response.getMessageId());

        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }
}
