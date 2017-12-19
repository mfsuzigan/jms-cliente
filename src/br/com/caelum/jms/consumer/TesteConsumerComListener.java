package br.com.caelum.jms.consumer;

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

		System.out.println("Criando consumer de mensagens");
		JmsUtils utils = new JmsUtils();
		Destination filaFinanceiro = utils.getDestination("financeiro");

		try {
			Session session = utils.getSession();
			MessageConsumer consumer = session.createConsumer(filaFinanceiro);

			System.out.println("Atribuindo listener");
			consumer.setMessageListener(new TesteClienteTextListener());

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Fechando recursos");
		utils.closeResources();
	}
}
