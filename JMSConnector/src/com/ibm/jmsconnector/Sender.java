package com.ibm.jmsconnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ibm.jmsconnector.common.Constants;
import com.ibm.jmsconnector.common.Util;

public class Sender implements Constants {

    public static void main(String[] args) {
	try {
	    //Start Connection
	    InitialContext initialContext = Util.getInitialContext();
	    
	    
	    TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) initialContext.lookup(TOPIC_CONNECTION_FACTORY);
	    TopicConnection topicConnection = topicConnectionFactory.createTopicConnection(JMS_USERNAME, JMS_PASSWORD);
	    topicConnection.start();
	    
	    //Create Queue Session
	    TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	    Topic topic = (Topic) initialContext.lookup(TOPIC);
	    //Create TopicPublisher
	    TopicPublisher topicPublisher = topicSession.createPublisher(topic);
	    //Create TextMessage
	    TextMessage textMessage = topicSession.createTextMessage();
	    
	    //Write Message
	    //TODO: Replace with byteMessage
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	    
	    while(true) {
		System.out.println("Enter message, or type exit to Close Publisher:");
		try {
		    String stringMessage = bufferedReader.readLine();
		    if(stringMessage.equals("exit")) {
			break;
		    }
		    textMessage.setText(stringMessage);
		    //Send Message
		    topicPublisher.publish(textMessage);
		    System.out.println("Message Sent.");
		} catch (IOException e) {
		    System.out.println(e);
		}
		
	    }
	    //Close Connection
	    topicConnection.close();
		    
	} catch (NamingException | JMSException e) {
	   System.out.println(e);
	}
    }

}
