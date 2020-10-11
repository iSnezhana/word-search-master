package com.bhatman.proto.wordsearch;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordSearchApplication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(WordSearchApplication.class);
	private static final String Big_FilePath = "big.txt";
	private static final int ThusandLines = 1000;

	public static void main(String[] args) {
		SpringApplication.run(WordSearchApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws IOException, URISyntaxException {
		logger.info("Starting WordSearch Application ...");
		File inputFile = new File(WordSearchApplication.class.getClassLoader().getResource(Big_FilePath).toURI());
		LineIterator lineIterator = FileUtils.lineIterator(inputFile, "UTF-8");

		try {
			int i = 0;
			while (lineIterator.hasNext() && i < ThusandLines) {
				i++;
			}
			logger.info("Read " + i + " lines from " + Big_FilePath);

			if (!lineIterator.hasNext()) {
				logger.info("Completed reading " + Big_FilePath);
			}
		} finally {
			LineIterator.closeQuietly(lineIterator);
		}

		logger.info("Shuting down WordSearch Application!");
	}

}
