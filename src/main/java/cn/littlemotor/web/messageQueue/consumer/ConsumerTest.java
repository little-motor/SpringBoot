package cn.littlemotor.web.messageQueue.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
@RabbitListener(queues = "test")
public class ConsumerTest {

  @RabbitHandler
  public void print(String content) {
    System.out.println(content);
  }
}
