package com.bhatman.proto.wordsearch;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordSearchApplication implements ApplicationRunner {

	@Inject
	WordSearch wordSearch;
	
	public static void main(String[] args) {
		SpringApplication.run(WordSearchApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws IOException, URISyntaxException {
		wordSearch.run();
	}

}
