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
public class TesteConsumerComOutrosAcks {

	/**
	 * Consome mensagens do topico de estoque
	 */
	public static void main(String[] args) {

		String topicName = "estoque";
		System.out.println("Criando consumer de mensagens. Destination (topico): " + topicName);
		JmsUtils utils = new JmsUtils();
		Topic topico = (Topic) utils.getDestination(topicName);

		try {
			Session session = utils.getSession(true, Session.SESSION_TRANSACTED);
			MessageConsumer consumer = session.createDurableSubscriber(topico,
					"topicoEstoqueSubscriptionComOutrosAcks");

			System.out.println("Atribuindo listener");
			consumer.setMessageListener(new TesteClienteTextListener());
			session.commit();

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Fechando recursos");
		utils.closeResources();
	}
}
