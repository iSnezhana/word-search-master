package com.bhatman.proto.wordsearch.model;

import java.util.ArrayList;
import java.util.List;

public class WSPart {
	private int partIndex;
	private int partLineCount;
	private List<WSLineDetails> lines = new ArrayList<>();

	public WSPart(int partIndex, int partLineCount) {
		this.partIndex = partIndex;
		this.partLineCount = partLineCount;
	}

	public void addLine(String line, int lineIndex) {
		lines.add(new WSLineDetails(line, (partLineCount * partIndex) + lineIndex));
	}

	public List<WSLineDetails> getLines() {
		return lines;
	}

}