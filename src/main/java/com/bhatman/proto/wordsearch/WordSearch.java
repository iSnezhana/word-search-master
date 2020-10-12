package com.bhatman.proto.wordsearch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bhatman.proto.wordsearch.model.WSPart;
import com.bhatman.proto.wordsearch.modules.WSAggregator;
import com.bhatman.proto.wordsearch.modules.WSFileReader;
import com.bhatman.proto.wordsearch.modules.WSMatcher;

@Named
public class WordSearch {

	private static final Logger logger = LoggerFactory.getLogger(WordSearch.class);
	private static final String FilePath = "big.txt";
	//private static final String FilePath = "small.txt";
	private static final int ThusandLines = 1000;
	private static Instant start;

	private WSFileReader wsFileReader;
	private WSMatcher wsMatcher;
	private WSAggregator wsAggregator;

	@Inject
	public WordSearch(WSFileReader wsFileReader, WSMatcher wsMatcher, WSAggregator wsAggregator) {
		this.wsFileReader = wsFileReader;
		this.wsMatcher = wsMatcher;
		this.wsAggregator = wsAggregator;
	}

	public void run() throws IOException, URISyntaxException {
		start = Instant.now();
		logger.info("Starting WordSearch Application ...");

		wsFileReader.readFileInParts(FilePath, ThusandLines);
		int partIndex = 0;
		while (wsFileReader.hasParts()) {
			WSPart wsPart = wsFileReader.getNextPart(partIndex);
			wsAggregator.recordResultsForPart(wsMatcher.getResultForPart(wsPart));
			partIndex++;
		}

		wsAggregator.printResultsToFile();
		long timeElapsed = Duration.between(start, Instant.now()).toMillis();

		logger.info("WordSearch Application took " + timeElapsed + " milliseconds to complete!");
	}

	protected WSAggregator getWsAggregator() {
		return wsAggregator;
	}

}
