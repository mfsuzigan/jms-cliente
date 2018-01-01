package br.com.caelum.jms.consumer.fila;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import br.com.caelum.jms.listener.TesteClienteTextListener;
import br.com.caelum.jms.util.JmsUtils;

/**
 *
 * @author Michel F. Suzigan
 *
 */
public class TesteConsumerComListener {

	/**
	 * Consome mensagens da fila de financeiro
	 */
	public static void main(String[] args) {

		String destinationName = "log";
		System.out.println("Criando consumer de mensagens. Destination: " + destinationName);
		JmsUtils utils = new JmsUtils();
		Destination destination = utils.getDestination(destinationName);

		try {
			Session session = utils.getSession();
			MessageConsumer consumer = session.createConsumer(destination);

			System.out.println("Atribuindo listener");
			consumer.setMessageListener(new TesteClienteTextListener());

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Fechando recursos");
		utils.closeResources();
	}
}
