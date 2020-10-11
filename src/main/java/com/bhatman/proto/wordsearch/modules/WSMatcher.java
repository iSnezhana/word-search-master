package com.bhatman.proto.wordsearch.modules;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.bhatman.proto.wordsearch.model.WSPart;

@Named
public class WSMatcher {

	@Inject
	private WSDictionary wsDict;

	@Inject
	private WSResults wsResults;

	@Inject
	public WSMatcher(WSDictionary dictionary) {
		wsDict = dictionary;
	}

	public WSResults match(WSPart wsPart) {
		WSResults results = new WSResults();
		List<String> lines = wsPart.getLines();

		lines.forEach(line -> {
			// implement matching here
			results.recordResult("word", 106, 39);
		});

		return new WSResults();
	}

}
