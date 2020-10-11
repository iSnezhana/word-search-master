package com.bhatman.proto.wordsearch.modules;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

import org.apache.commons.io.FileUtils;

@Named
public class WSDictionary {

	private static final String Dictionary_FilePath = "dictionary.txt";
	private Set<String> dictionaryWords = new HashSet<String>();

	public WSDictionary() throws IOException, URISyntaxException {
		File inputFile = new File(WSDictionary.class.getClassLoader().getResource(Dictionary_FilePath).toURI());
		String dictionaryString = FileUtils.readFileToString(inputFile, "UTF-8");
		dictionaryString.split(",");
		dictionaryWords.addAll(Arrays.asList(dictionaryString.split(",")));
	}

	public Set<String> getDictionaryWords() {
		return dictionaryWords;
	}

}
