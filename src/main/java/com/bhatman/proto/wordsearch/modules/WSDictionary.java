package com.bhatman.proto.wordsearch.modules;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

@Named
public class WSDictionary {

	Set<String> dictWord = new HashSet<String>();

	public WSDictionary() {
		super();
		dictWord.add("James");
	}

}
