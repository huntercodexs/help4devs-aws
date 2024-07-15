package com.huntercodexs.demo.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Help4DevsAwsJmsSqsLister implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Message received: " + ((TextMessage)message).getText());
        } catch (JMSException je) {
            throw new RuntimeException(je.getMessage());
        }
    }
}
