package com.bhatman.proto.wordsearch;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bhatman.proto.wordsearch.model.WSPart;
import com.bhatman.proto.wordsearch.modules.WSAggregator;
import com.bhatman.proto.wordsearch.modules.WSFileReader;
import com.bhatman.proto.wordsearch.modules.WSMatcher;
import com.bhatman.proto.wordsearch.modules.WSResults;

@SpringBootApplication
public class WordSearchApplication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(WordSearchApplication.class);
	private static final String Big_FilePath = "big.txt";
	private static final int ThusandLines = 1000;
	
	@Inject
	private WSFileReader wsFileReader;

	@Inject
	private WSMatcher wsMatcher;

	@Inject
	private WSAggregator wsAggregator;
	
	public static void main(String[] args) {
		SpringApplication.run(WordSearchApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws IOException, URISyntaxException {
		logger.info("Starting WordSearch Application ...");
		
		wsFileReader.readFileInParts(Big_FilePath, ThusandLines);

		while (wsFileReader.hasParts()) {
			WSPart wsPart = wsFileReader.getNextPart();
			WSResults wsResults = wsMatcher.match(wsPart);
			wsAggregator.addResults(wsResults);
		}

		wsAggregator.printResults();

		logger.info("Shuting down WordSearch Application!");
	}

}
