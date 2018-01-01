package br.com.caelum.jms.producer.fila;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import br.com.caelum.jms.util.JmsUtils;

public class TesteProducerComPrioridade {

	public static void main(String[] args) {

		JmsUtils utils = new JmsUtils();
		Destination filaLog = utils.getDestination("log");

		Session session = utils.getSession();

		try {
			MessageProducer producer = session.createProducer(filaLog);
			TextMessage textMessage = session.createTextMessage("Mensagem exemplo com prioridade 8");

			textMessage = session.createTextMessage("Mensagem exemplo com prioridade 0");
			producer.send(textMessage, DeliveryMode.NON_PERSISTENT, 0, 2000);

			textMessage = session.createTextMessage("Mensagem exemplo com prioridade 8");
			producer.send(textMessage, DeliveryMode.NON_PERSISTENT, 8, 60000);

			textMessage = session.createTextMessage("Mensagem exemplo com prioridade 5");
			producer.send(textMessage, DeliveryMode.NON_PERSISTENT, 5, 60000);

			producer.close();
			utils.closeResources();

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
