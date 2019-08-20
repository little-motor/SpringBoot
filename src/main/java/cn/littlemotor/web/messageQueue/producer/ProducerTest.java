package cn.littlemotor.web.messageQueue.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerTest {

  @Autowired
  AmqpTemplate amqpTemplate = null;

  public void testProducer() {
    for (int i = 0; i < 10; i++) {
      amqpTemplate.convertAndSend("fanout", "", "hello world");
    }
  }
}
