package com.bhatman.proto.wordsearch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;

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

@SpringBootApplication
public class WordSearchApplication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(WordSearchApplication.class);
	private static final String FilePath = "big.txt";
	//private static final String FilePath = "small.txt";
	private static final int ThusandLines = 1000;
	private static Instant start;

	@Inject
	private WSFileReader wsFileReader;

	@Inject
	private WSMatcher wsMatcher;

	@Inject
	private WSAggregator wsAggregator;

	public static void main(String[] args) {
		start = Instant.now();
		SpringApplication.run(WordSearchApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws IOException, URISyntaxException {
		logger.info("Starting WordSearch Application ...");

		wsFileReader.readFileInParts(FilePath, ThusandLines);
		int partIndex = 0;
		while (wsFileReader.hasParts()) {
			WSPart wsPart = wsFileReader.getNextPart(partIndex);
			wsAggregator.recordResultsForPart(wsMatcher.getResultForPart(wsPart));
			partIndex++;
		}

		wsAggregator.printResults();
		long timeElapsed = Duration.between(start, Instant.now()).toMillis();

		logger.info("WordSearch Application took " + timeElapsed + " milliseconds to complete!");
	}

}
