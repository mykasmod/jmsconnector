package com.ibm.jmsconnector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener {
    private boolean isExitMessage = false;

    @Override
    public void onMessage(Message message) {
	try {
	    TextMessage textMessage = (TextMessage) message;
	    System.out.println("Message Received:" + textMessage.getText());
	    if(textMessage.getText().equals("exit receiver")) {
		//TODO: throws exception to calling class.
	    }
	    
	} catch (JMSException e) {
	   System.out.println(e);
	}
	
    }
    
   

    public boolean isExitMessage() {
        return isExitMessage;
    }

    public void setExitMessage(boolean isExitMessage) {
        this.isExitMessage = isExitMessage;
    }
    
    
   

}
