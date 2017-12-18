package br.com.caelum.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.jms.listener.TesteClienteTextListener;

public class TesteClienteComListener {

	public static void main(String[] args) throws NamingException, JMSException {

		System.out.println("Criando consumer de mensagens");
		InitialContext context = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination filaFinanceiro = (Destination) context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(filaFinanceiro);

		System.out.println("Atribuindo listener");
		consumer.setMessageListener(new TesteClienteTextListener());

		System.out.println("Fechando recursos");
		consumer.close();
		session.close();
		connection.close();
	}
}
