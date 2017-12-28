package br.com.caelum.jms.producer.fila;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import br.com.caelum.jms.util.JmsUtils;

public class TesteProducer {

	public static void main(String[] args) {

		JmsUtils utils = new JmsUtils();
		Destination filaFinanceiro = utils.getDestination("financeiro");

		Session session = utils.getSession();

		try {
			MessageProducer producer = session.createProducer(filaFinanceiro);

			for (int i = 1; i < 11; i++) {
				System.out.println("Criando mensagem (fila) " + i);
				TextMessage textMessage = session.createTextMessage("Mensagem exemplo " + i);
				producer.send(textMessage);
			}

			producer.close();
			utils.closeResources();

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
