package cn.littlemotor.web.messageQueue.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列配置
 */
@Configuration
public class FanoutExchangeConfig {

  @Bean(name = "AQueue")
  public Queue AQueue() {
    return new Queue("A", false);
  }

  @Bean(name = "BQueue")
  public Queue BQueue() {
    return new Queue("B", false);
  }

  @Bean(name = "CQueue")
  public Queue CQueue() {
    return new Queue("C", false);
  }

  @Bean
  public FanoutExchange createFanoutExchange(){
    return new FanoutExchange("fanout");
  }

  @Bean
  public Binding bindingA(@Qualifier(value = "AQueue") Queue queue, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(queue).to(fanoutExchange);
  }

  @Bean
  public Binding bindingB(@Qualifier(value = "BQueue") Queue queue, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(queue).to(fanoutExchange);
  }

  @Bean
  public Binding bindingC(@Qualifier(value = "CQueue") Queue queue, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(queue).to(fanoutExchange);
  }
}
