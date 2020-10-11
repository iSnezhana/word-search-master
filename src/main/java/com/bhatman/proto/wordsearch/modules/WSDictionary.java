package com.bhatman.proto.wordsearch.modules;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

@Named
public class WSDictionary {

	private Set<String> dictionaryWords = new HashSet<String>();

	public WSDictionary() {
		dictionaryWords.add("James");
	}

	public Set<String> getDictionaryWords() {
		return dictionaryWords;
	}

}
