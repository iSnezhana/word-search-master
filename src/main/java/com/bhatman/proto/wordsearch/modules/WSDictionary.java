package com.bhatman.proto.wordsearch.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class WSDictionary {

	private static final Logger logger = LoggerFactory.getLogger(WSDictionary.class);
	private static final String Dictionary_FilePath = "dictionary.txt";
	private Set<String> dictionaryWords = new HashSet<String>();

	public WSDictionary() throws IOException, URISyntaxException {
		String dictionaryString = readFileAsString();
		logger.info("Loaded dictionary --> " + dictionaryString);
		dictionaryWords.addAll(Arrays.asList(dictionaryString.split(",")));
	}

	private String readFileAsString() throws IOException {
		InputStream in = WSDictionary.class.getClassLoader().getResourceAsStream(Dictionary_FilePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String dictionaryString = reader.readLine();
		reader.close();
		
		return dictionaryString;
	}

	public Set<String> getDictionaryWords() {
		return dictionaryWords;
	}

}
