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
public class TesteConsumerParaObjeto {

	/**
	 * Consome mensagens do topico de estoque
	 */
	public static void main(String[] args) {

		String topicName = "estoque";
		System.out.println("Criando consumer de mensagens com selector. Destination (topico): " + topicName);
		JmsUtils utils = new JmsUtils();
		Topic topico = (Topic) utils.getDestination(topicName);

		try {
			Session session = utils.getSession();
			MessageConsumer consumer = session.createDurableSubscriber(topico, "topicoEstoqueSubscriptionSelector",
					"temSelector is null OR temSelector=true", false);

			System.out.println("Atribuindo listener");
			consumer.setMessageListener(new TesteClienteTextListener());

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Fechando recursos");
		utils.closeResources();
	}
}
