# Word-Search 
###### Description -  Prototype application for finding words in a file from a dictionary
###### Author - `Pravin Bhat`

## Details
A simple Java program to find specific strings in a large text. The program should be composed of the following modules:
1. The main module - reads a large text file in parts (e.g. 1000 lines in each part) andvsends each part (as string) to a matcher. After all matchers completed, it calls the aggregator to combine and print the results
2. The matcher - gets a text string as input and searches for matches of a given set of strings. The result is a map from a word to its location(s) in the text
3. The aggregator - aggregates the results from all the matchers and prints the results. For this task, please use the text at http://norvig.com/big.txt , and the strings to find should be the 50 most common English first names:
4. Dictionary – Keep the words to be used in appropriate data structure and make it available through this module.
    * Sample dictionary: James,John,Robert,Michael,William,David,Richard,Charles,Joseph,Thomas,Christopher,Daniel,Paul,Mark,Donald,George,Kenneth,Steven,Edward,Brian,Ronald,Anthony,Kevin,Jason,Matthew,Gary,Timothy,Jose,Larry,Jeffrey,Frank,Scott,Eric,Stephen,Andrew,Raymond,Gregory,Joshua,Jerry,Dennis,Walter,Patrick,Peter,Harold,Douglas,Henry,Carl,Arthur,Ryan,Roger

### Additional instructions
The results should be printed (in no particular order) after all text pieces have been processed. 
The results can also be made available through an API.
#### Sample output
Timothy --> [[lineOffset=13000, charOffset=19775], [lineOffset=13000, charOffset=42023]]

## Development

### Prerequisite
    Java 11+
    Maven 3.6+

### Initializing the app
This application was initialized from start.spring.io as below
https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.3.4.RELEASE&packaging=jar&jvmVersion=11&groupId=com.bhatman.proto&artifactId=word-search&name=word-search&description=Search%20word%20from%20a%20dictionary&packageName=com.bhatman.proto.word-search

### Building the app
`mvnw clean package`

### Running the app
`mvnw spring-boot:run`  OR  `java -jar target\word-search-1.0.1.jar`
###### Output file `word-search-result.txt` with the results will be created in same folder.
