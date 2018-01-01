package br.com.caelum.jms.producer.topico;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import br.com.caelum.jms.util.JmsUtils;
import br.com.caelum.modelo.Pedido;
import br.com.caelum.modelo.PedidoFactory;

public class TesteProducerComObjeto {

	public static void main(String[] args) {

		JmsUtils utils = new JmsUtils();
		Destination topicoEstoque = utils.getDestination("estoque");

		Session session = utils.getSession();

		try {
			MessageProducer producer = session.createProducer(topicoEstoque);

			for (int i = 1; i < 11; i++) {
				System.out.println("Criando mensagem " + i + " com objeto pedido para topico");
				Pedido pedido = new PedidoFactory().geraPedidoComValores();
				ObjectMessage objectMessage = session.createObjectMessage(pedido);
				producer.send(objectMessage);
			}

			producer.close();
			utils.closeResources();

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
