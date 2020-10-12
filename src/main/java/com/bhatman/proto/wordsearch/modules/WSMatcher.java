package com.bhatman.proto.wordsearch.modules;

import java.util.ArrayList;
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

	public WSResults getResultForPart(WSPart wsPart) {
		WSResults results = new WSResults();

		wsPart.getLines().forEach(line -> {
			dictionaryWords.forEach(word -> {
				List<String> matches = getMatchesByLineAndWord(line, word);
				if (!matches.isEmpty()) {
					results.recordResult(word, matches);
				}
			});
		});

		return results;
	}

	private List<String> getMatchesByLineAndWord(WSLineDetails line, String word) {
		int charOffset = 0;
		int wordLength = 0;
		List<String> matches = new ArrayList<>();
		while (charOffset != -1) {
			charOffset = line.getLine().indexOf(word, charOffset + wordLength);
			if (charOffset != -1) {
				matches.add("[lineOffset=" + line.getLineIndex() + ", charOffset=" + (charOffset + 1) + "]");
			}
			wordLength = word.length();
		}
		
		return matches;
	}

}
