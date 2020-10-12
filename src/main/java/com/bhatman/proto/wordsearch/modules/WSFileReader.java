package com.bhatman.proto.wordsearch.modules;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import javax.inject.Named;

import org.apache.commons.io.IOUtils;
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
		this.partLineCount = partLineCount;
		hasParts = true;
		InputStream in = WSFileReader.class.getClassLoader().getResourceAsStream(inputFilePath);
		lineIterator = IOUtils.lineIterator(in, Charset.forName("utf-8"));
		logger.info("Starting to read " + inputFilePath);
	}

	public WSPart getNextPart(int partIndex) throws IOException {
		WSPart wsPart = new WSPart(partIndex, partLineCount);
		int i = 1;
		try {
			while (lineIterator.hasNext() && i <= partLineCount) {
				wsPart.addLine(lineIterator.nextLine(), i);
				i++;
			}
			logger.debug("Read " + i + " lines from " + inputFilePath);

			if (!lineIterator.hasNext()) {
				hasParts = false;
				logger.info("Completed reading " + inputFilePath);
				lineIterator.close();
			}
		} finally {
		}

		return wsPart;
	}

	public boolean hasParts() {
		return hasParts;
	}

}
