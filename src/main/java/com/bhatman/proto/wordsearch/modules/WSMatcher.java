package com.bhatman.proto.wordsearch.modules;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import com.bhatman.proto.wordsearch.model.WSLineDetails;
import com.bhatman.proto.wordsearch.model.WSPart;

@Named
public class WSMatcher {

	private Set<String> dictionaryWords;

	@Inject
	public WSMatcher(WSDictionary dictionary) {
		dictionaryWords = dictionary.getDictionaryWords();
	}

	public WSResults match(WSPart wsPart) {
		WSResults results = new WSResults();
		List<WSLineDetails> lines = wsPart.getLines();

		lines.forEach(line -> {
			dictionaryWords.forEach(word -> {
				int charOffset = line.getLine().indexOf(word);
				if (-1 != charOffset) {
					results.recordResult(word, line.getLineIndex(), charOffset + 1);
				}
			});
		});

		return results;
	}

}
