package  uy.com.netlabs.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Component
public class MessageSender {

    @Autowired
    private JmsTemplate template;

    public void sendMessage(final String queueName, final String selector, final String message) throws Exception {
        try {
            template.send(queueName, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    ObjectMessage createdMessage = session.createObjectMessage(message);
                    return createdMessage;
                }
            });
        } catch (JmsException e) {
            throw new Exception(
                    "Sending message to transaction (message is " + message + ")", e);
        }
    }
}
