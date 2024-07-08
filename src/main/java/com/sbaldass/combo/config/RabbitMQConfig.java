package com.sbaldass.combo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Binding binding(@Qualifier("motoboyRequestsQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("motoboyRequestsRoutingKey");
    }

    @Bean
    public Queue motoboyAcceptQueue() {
        return new Queue("motoboyAcceptQueue", false);
    }

    @Bean
    public Binding bindingAccept(@Qualifier("motoboyAcceptQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("motoboyAcceptRoutingKey");
    }

    @Bean
    public Queue motoboyRejectQueue() {
        return new Queue("motoboyRejectQueue", false);
    }

    @Bean
    public Binding bindingReject(@Qualifier("motoboyRejectQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("motoboyRejectRoutingKey");
    }
}