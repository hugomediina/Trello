package org.example.Trello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class TrelloApplication implements CommandLineRunner {
	private static final Logger log = Logger.getLogger(TrelloApplication.class.getName());
	public static void main(String[] args) {
		new SpringApplicationBuilder(TrelloApplication.class).run(args);
	}
	@Override
	public void run(String... args) throws Exception {
	}
}
