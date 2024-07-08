package com.sbaldass.combo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue motoboyRequestsQueue() {
        return new Queue("motoboyRequestsQueue", false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("motoboyRequestsExchange");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("motoboyRequestsRoutingKey");
    }
}
