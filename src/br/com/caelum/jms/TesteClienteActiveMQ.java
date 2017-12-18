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

public class TesteClienteActiveMQ {

	public static void main(String[] args) throws NamingException, JMSException {

		System.out.println("Buscando contexto JNDI");
		InitialContext initialContext = new InitialContext();

		// lookup para a connection factory
		ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");

		System.out.println("Criando conexao com o ActiveMQ");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// false = nao utilizar transacoes
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// lookup para a destination (fila)a
		Destination filaFinanceiro = (Destination) initialContext.lookup("financeiro");

		MessageConsumer consumer = session.createConsumer(filaFinanceiro);
		Message message = consumer.receive();

		if (message != null) {
			System.out.println("Recebendo mensagem " + message);
		}

		System.out.println("Fechando recursos");

		consumer.close();
		connection.close();
		initialContext.close();
	}
}
