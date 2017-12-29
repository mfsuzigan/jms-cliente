package br.com.caelum.jms.listener;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TesteClienteTextListener implements MessageListener {

	@Override
	public void onMessage(Message message) {

		if (TextMessage.class.isAssignableFrom(message.getClass())) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println("Recebendo mensagem pelo listener: " + textMessage.getText() + ", propriedades: ["
						+ textMessage.getPropertyNames() + "], [" + new Date(textMessage.getJMSTimestamp()) + "]");

			} catch (JMSException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
