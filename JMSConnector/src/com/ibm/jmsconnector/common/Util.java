package com.ibm.jmsconnector.common;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Util implements Constants {

    public static InitialContext getInitialContext() throws NamingException {
	Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, JMS_CONNECTION_FACTORY_JNDI);
        props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);  
        props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
        props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
       // props.put("jboss.naming.client.ejb.context", true);
        InitialContext initialContext = new InitialContext(props); 
	return initialContext;
    }

}
