package com.bhatman.proto.wordsearch.model;

public class WSLineDetails {
	private String line;
	private int lineIndex;

	public WSLineDetails(String line, int lineIndex) {
		this.line = line;
		this.lineIndex = lineIndex;
	}

	public String getLine() {
		return line;
	}

	public int getLineIndex() {
		return lineIndex;
	}

}