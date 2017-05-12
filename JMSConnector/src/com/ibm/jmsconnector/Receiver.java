package com.ibm.jmsconnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ibm.jmsconnector.common.Constants;
import com.ibm.jmsconnector.common.Util;

public class Receiver implements Constants {

    public static void main(String[] args) {
	try {
	      
	    InitialContext initialContext = Util.getInitialContext(); 
	    
	    TopicConnectionFactory topicConnectionFactory=(TopicConnectionFactory)initialContext.lookup(TOPIC_CONNECTION_FACTORY);
	    TopicConnection topicConnection = topicConnectionFactory.createTopicConnection(JMS_USERNAME, JMS_PASSWORD);
	    topicConnection.start();
	    
	    TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	    Topic topic = (Topic)initialContext.lookup(TOPIC);
	    //Create TopicSubscriber
	    TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);
	    
	    Listener listener = new Listener();	    
	    //Register listener with Subscriber
	    topicSubscriber.setMessageListener(listener);
	    
	    System.out.println("Subscriber waiting for messages...");	
	    //TODO: System.out.println("if recvd msg is exit receiver, the Subscriber will close");
	    System.out.println("Type exit to Close Subscriber:");
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
	    while(true) {
		try {
		    String stringMessage = bufferedReader.readLine();
		    if(stringMessage.equals("exit")) {
			break;
		    }
		    Thread.sleep(1000);
		} catch (InterruptedException | IOException e) {
		    System.out.println(e);
		}
	    }
	    
	} catch (NamingException | JMSException  e) {
	   System.out.println(e);
	}
		
    }

}
