package br.com.caelum.jms.listener;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public class TesteClienteTextListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;

				System.out.println("Recebendo mensagem de texto pelo listener: " + textMessage.getText()
						+ ", propriedades: [" + textMessage.getPropertyNames() + "], ["
						+ new Date(textMessage.getJMSTimestamp()) + "]");

			} else if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;

				System.out.println("Recebendo mensagem com objeto pelo listener: " + objectMessage.getObject()
						+ ", propriedades: [" + objectMessage.getPropertyNames() + "], ["
						+ new Date(objectMessage.getJMSTimestamp()) + "]");
			}

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
