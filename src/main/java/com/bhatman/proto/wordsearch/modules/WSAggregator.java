package com.bhatman.proto.wordsearch.modules;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class WSAggregator {
	private static final Logger logger = LoggerFactory.getLogger(WSAggregator.class);

	private Map<String, List<String>> results = new TreeMap<String, List<String>>();

	public void recordResultsForPart(WSResults wsResults) {
		Map<String, List<String>> partResults = wsResults.getResults();
		partResults.forEach((word, result) -> {
			if (results.containsKey(word)) {
				List<String> wordResult = results.get(word);
				wordResult.addAll(result);
			} else {
				results.put(word, result);
			}
		});
	}

	public void printResultsToFile() throws IOException {
		FileWriter fileWriter = new FileWriter("word-search-result.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    results.forEach((word, result) -> {
			result.forEach(resultLine -> {
				printWriter.printf("%s --> %s\n", word, resultLine);
				//System.out.println(word + "--> " + resultLine);
			});
		});
	    printWriter.close();
	    
	    logger.info("==============> Search result have been added to file: word-search-result.txt");
	}
	
	public String getResultsByWord(String word, int lineIndex) {
		List<String> result = results.get(word);
		return result.stream().filter(match -> match.contains("[[lineOffset=" + lineIndex)).findAny().orElse("");
	}

}
