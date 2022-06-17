package net.timenation.timevelocityapi.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ {

    private final ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    public RabbitMQ() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setUsername("admin");
        this.connectionFactory.setPassword("9cL9RttK6Sn5t9Wb4iqgM68jN3Q3KK");

        try {
            this.connection = this.connectionFactory.newConnection();
            this.channel = this.connection.createChannel();
        } catch (IOException | TimeoutException exception) {
            exception.printStackTrace();
        }
    }

    public void sendMessageToRabbtiMQ(String message) throws IOException {
        this.channel.basicPublish("discord-status", "", null, message.getBytes());
    }
}
