package com.gpt.wx;

import io.github.asleepyfish.annotation.EnableChatGPT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableChatGPT

public class WeChatGPTApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeChatGPTApplication.class, args);
  }
}
