package net.timenation.timevelocityapi.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import net.timenation.timevelocityapi.TimeVelocityAPI;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ {

    private final ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    public RabbitMQ() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setUsername("admin");
        this.connectionFactory.setPassword(TimeVelocityAPI.getInstance().getTimeConfig().getRabbitMQCredentials().getPassword());

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
