package dev.bensantosa.runners;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import dev.bensantosa.runners.user.User;
import dev.bensantosa.runners.user.UserHttpClient;

@SpringBootApplication
public class RunnersApplication {

  private static final Logger logger = LoggerFactory.getLogger(RunnersApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(RunnersApplication.class, args);
    logger.info("Application started successfully");
  }

  @Bean
  UserHttpClient userHttpClient() {
    RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
    return factory.createClient(UserHttpClient.class);
  }

  @Bean
  CommandLineRunner runner(UserHttpClient client) {
    return args -> {
      List<User> users = client.findAll();
      System.out.println(users);

      User user = client.findById(1);
      System.out.println(user);
    };
  }

}
