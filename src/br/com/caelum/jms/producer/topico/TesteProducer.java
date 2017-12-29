package br.com.caelum.jms.producer.topico;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import br.com.caelum.jms.util.JmsUtils;

public class TesteProducer {

	public static void main(String[] args) {

		JmsUtils utils = new JmsUtils();
		Destination topicoEstoque = utils.getDestination("estoque");

		Session session = utils.getSession();

		try {
			MessageProducer producer = session.createProducer(topicoEstoque);

			for (int i = 1; i < 11; i++) {
				System.out.println("Criando mensagem (topico) " + i);
				TextMessage textMessage = session.createTextMessage("Mensagem exemplo " + i);
				// textMessage.setBooleanProperty("temSelector", true);
				producer.send(textMessage);
			}

			producer.close();
			utils.closeResources();

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
