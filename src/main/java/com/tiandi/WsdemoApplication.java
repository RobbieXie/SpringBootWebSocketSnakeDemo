package com.tiandi;

import com.tiandi.snake.SnakeWebSocketHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableWebSocket
public class WsdemoApplication extends SpringBootServletInitializer
	implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(echoWebSocketHandler(), "/echo").withSockJS();
		registry.addHandler(snakeWebSocketHandler(), "/snake").withSockJS();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WsdemoApplication.class);
	}

	@Bean
	public WebSocketHandler snakeWebSocketHandler() {
		return new PerConnectionWebSocketHandler(SnakeWebSocketHandler.class);
	}


	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	public static void main(String[] args) {
		SpringApplication.run(WsdemoApplication.class, args);
	}
}
