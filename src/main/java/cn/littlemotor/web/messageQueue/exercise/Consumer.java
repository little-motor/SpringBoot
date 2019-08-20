package cn.littlemotor.web.messageQueue.exercise;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

  public static final String IP_ADDRESS = "localhost";
  public static final int PORT = 5672;

  public static void main(String[] args) throws IOException, TimeoutException {
    Address[] addresses = new Address[]{
        new Address(IP_ADDRESS, PORT)
    };
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setUsername("littlemotor");
    connectionFactory.setPassword("123456");
    connectionFactory.newConnection(addresses);
    Connection connection = connectionFactory.newConnection(addresses);
    Channel channel = connection.createChannel();
    channel.basicQos(64);
    com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){

    } ;
  }
}
