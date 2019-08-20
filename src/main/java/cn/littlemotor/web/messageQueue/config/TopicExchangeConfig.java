package cn.littlemotor.web.messageQueue.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

public class TopicExchangeConfig {

  @Bean
  public Queue createQueueA(){
    return new Queue("topicA");
  }

  @Bean
  public Queue createQueueB(){
    return new Queue("topicB");
  }

  @Bean
  public Queue createQueueC(){
    return new Queue("topicC");
  }


  @Bean
  public TopicExchange createTopicExchangeA(){
    return new TopicExchange("topicA");
  }

  @Bean
  public TopicExchange createTopicExchangeB(){
    return new TopicExchange("topicB");
  }
}
