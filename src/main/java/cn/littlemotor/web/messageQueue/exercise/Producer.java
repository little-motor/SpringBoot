package cn.littlemotor.web.messageQueue.exercise;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

  public static final String HOST_ADDRESS = "localhost";
  public static final int PORT = 5672;
  public static final String USER_NAME = "littlemotor";
  public static final String PASSWORD = "123456";
  public static final String QUEUE_NAME = "queue";
  public static final String ROUTING_KEY = "routing_key";


  public static void main(String[] args) throws IOException, TimeoutException {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost(HOST_ADDRESS);
    connectionFactory.setPort(PORT);
    connectionFactory.setUsername(USER_NAME);
    connectionFactory.setPassword(PASSWORD);
    Connection connection = connectionFactory.newConnection();
    Channel channel = connection.createChannel();
    channel.exchangeDeclare(ExchangeTypes.DIRECT, "direct", true, false, null);
    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
    channel.queueBind(QUEUE_NAME, ExchangeTypes.DIRECT, ROUTING_KEY);
    String message = "hello world";
    channel.basicPublish(ExchangeTypes.DIRECT, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
    channel.close();
    connection.close();
  }
}
