package com.bhatman.proto.wordsearch;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WordSearchApplicationTests {
	
	@Inject
	WordSearch ws;

	@Test
	void wordSearchReturnsExpectedResults() {
		String result = ws.getWsAggregator().getResultsByWord("Peter", 2581);

		assertThat(result).isEqualTo("[[lineOffset=2581, charOffset=306],[lineOffset=2581, charOffset=385],[lineOffset=2581, charOffset=950],[lineOffset=2581, charOffset=1323]]");
	}

}
