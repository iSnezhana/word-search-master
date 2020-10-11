package com.bhatman.proto.wordsearch.modules;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bhatman.proto.wordsearch.model.WSPart;

@Named
public class WSFileReader {

	private static final Logger logger = LoggerFactory.getLogger(WSFileReader.class);

	private String inputFilePath;
	private LineIterator lineIterator;
	private int partLineCount;
	private boolean hasParts;

	public void readFileInParts(String inputFilePath, int partLineCount) throws IOException, URISyntaxException {
		this.inputFilePath = inputFilePath;
		File inputFile = new File(WSFileReader.class.getClassLoader().getResource(inputFilePath).toURI());
		lineIterator = FileUtils.lineIterator(inputFile, "UTF-8");
		this.partLineCount = partLineCount;
		hasParts = true;
		logger.info("Starting to read " + inputFilePath);
	}

	public WSPart getNextPart() throws IOException {
		WSPart wsPart = new WSPart();
		int i = 0;
		try {
			while (lineIterator.hasNext() && i < partLineCount) {
				wsPart.addLine(lineIterator.nextLine());
				i++;
			}
			logger.info("Read " + i + " lines from " + inputFilePath);

			if (!lineIterator.hasNext()) {
				hasParts = false;
				logger.info("Completed reading " + inputFilePath);
			}
		} finally {
			if (!hasParts) {
				LineIterator.closeQuietly(lineIterator);
			}
		}

		return wsPart;
	}

	public boolean hasParts() {
		return hasParts;
	}

}
