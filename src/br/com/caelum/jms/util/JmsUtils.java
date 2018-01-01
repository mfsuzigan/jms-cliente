package br.com.caelum.jms.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Classe utilitaria para operacoes de JMS
 *
 * @author michel
 *
 */
public class JmsUtils {

	private InitialContext context;
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;

	public JmsUtils() {

		try {
			context = context == null ? new InitialContext() : context;
			connectionFactory = connectionFactory == null ? (ConnectionFactory) context.lookup("ConnectionFactory")
					: connectionFactory;
			connection = connection == null ? connectionFactory.createConnection() : connection;
			connection.setClientID("clientId");
			connection.start();

		} catch (NamingException | JMSException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retorna uma {@link Session}
	 *
	 * @return
	 */
	public Session getSession() {
		return getSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	/**
	 * Retorna uma {@link Session} com as opcoes informadas
	 *
	 * @return
	 */
	public Session getSession(boolean deveUtilizarContextoTransacional, int sessionAcknowledgeStyle) {
		try {
			return session == null ? connection.createSession(deveUtilizarContextoTransacional, sessionAcknowledgeStyle)
					: session;

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retorna um {@link Destination} com base no nome
	 *
	 * @param name
	 * @return
	 */
	public Destination getDestination(String name) {
		try {
			return (Destination) context.lookup(name);

		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Fecha os recursos utilizados
	 *
	 * @return
	 */
	public boolean closeResources() {

		try {

			if (session != null) {
				session.close();
			}

			if (connection != null) {
				connection.close();
			}

			if (context != null) {
				context.close();
			}

		} catch (JMSException | NamingException e) {
			throw new RuntimeException(e);
		}

		return true;
	}
}
