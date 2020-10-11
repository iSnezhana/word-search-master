package com.bhatman.proto.wordsearch.model;

import java.util.ArrayList;
import java.util.List;

public class WSPart {
	private List<String> lines = new ArrayList<>();

	public void addLine(String nextLine) {
		lines.add(nextLine);
	}

	public List<String> getLines() {
		return lines;
	}

}
