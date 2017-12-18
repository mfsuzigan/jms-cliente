package br.com.caelum.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteClienteSimples {

	public static void main(String[] args) throws NamingException, JMSException {

		System.out.println("Buscando contexto JNDI");
		InitialContext context = new InitialContext();

		// lookup para a connection factory
		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");

		System.out.println("Criando conexao com o ActiveMQ");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// false = nao utilizar transacoes
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// lookup para a destination (fila)a
		Destination filaFinanceiro = (Destination) context.lookup("financeiro");

		MessageConsumer consumer = session.createConsumer(filaFinanceiro);

		// recebe apenas uma mensagem
		Message message = consumer.receive();

		// opcao para informar timeout de recebimento da mensagem
		// Message message = consumer.receive(10000)

		if (message != null) {
			System.out.println("Recebendo mensagem " + message);
		}

		System.out.println("Fechando recursos");

		consumer.close();
		session.close();
		connection.close();
		context.close();
	}
}
