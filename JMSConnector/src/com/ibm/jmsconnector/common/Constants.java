package com.ibm.jmsconnector.common;

public interface Constants {
    public static final String JMS_CONNECTION_FACTORY_JNDI = "org.jboss.naming.remote.client.InitialContextFactory";
    public static final String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";
    
    public static final String JMS_USERNAME = "user";
    public static final String JMS_PASSWORD = "user";
    
    public static final String TOPIC_CONNECTION_FACTORY = "java:jms/RemoteConnectionFactory";//"java:/jms/MyConnectionFactory"; //"java:jms/RemoteConnectionFactory";
    public static final String TOPIC = "java:jms/topic/MyTopic";
	

   
}
