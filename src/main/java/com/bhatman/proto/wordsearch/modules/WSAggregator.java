package com.bhatman.proto.wordsearch.modules;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Named;

@Named
public class WSAggregator {
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

	public void printResults() {
		results.forEach((word, result) -> {
			result.forEach(resultLine -> {
				System.out.println(word + "--> " + resultLine);
			});
		});
	}

}
