package com.bhatman.proto.wordsearch.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Named;

@Named
public class WSResults {

	private Map<String, List<String>> results = new TreeMap<String, List<String>>();

	public void recordResult(String word, int lineOffset, int charOffset) {
		String resultEntry = "lineOffset=" + lineOffset + ", charOffset=" + charOffset;

		if (!results.containsKey(word)) {
			results.put(word, new ArrayList<>());
		}

		results.get(word).add(resultEntry);
	}
}
