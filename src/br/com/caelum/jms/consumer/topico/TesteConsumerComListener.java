package br.com.caelum.jms.consumer.topico;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import br.com.caelum.jms.listener.TesteClienteTextListener;
import br.com.caelum.jms.util.JmsUtils;

/**
 *
 * @author Michel F. Suzigan
 *
 */
public class TesteConsumerComListener {

	/**
	 * Consome mensagens do topico de estoque
	 */
	public static void main(String[] args) {

		String topicName = "estoque";
		System.out.println("Criando consumer de mensagens. Destination (topico): " + topicName);
		JmsUtils utils = new JmsUtils();
		Topic topico = (Topic) utils.getDestination(topicName);

		try {
			Session session = utils.getSession();
			MessageConsumer consumer = session.createDurableSubscriber(topico, "topicoEstoqueSubscription");

			System.out.println("Atribuindo listener");
			consumer.setMessageListener(new TesteClienteTextListener());

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Fechando recursos");
		utils.closeResources();
	}
}
