package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import main.XmlReader;

class DataProcessingTest {
	
	protected String csvHeader = "";
	protected String csvTrailer = "";
	protected int csvBlockCount = -1;
	ArrayList<ArrayList<String>> csvData = new ArrayList<ArrayList<String>>(1);
	
	@BeforeEach
	void setUp() throws Exception {
		XmlReader xmlReader = new XmlReader();
		Document xmlInput = xmlReader.getXmlInput();
		Node node = xmlInput.getElementsByTagName("CSVIntervalData").item(0);
		Element eElement = (Element) node;
		String[] data = eElement.getTextContent().split("\n");		
		for(int i = 0; i < data.length; i++) {
			if(data[i].length() >= 3) {
				if(data[i].substring(0,3).matches("100")) { //set header for each csv file
					csvHeader = data[i];
				} else if(data[i].substring(0, 3).matches("200")) { //create new block for a new csv file
					csvBlockCount++; 
					ArrayList<String> csvBlock = new ArrayList<String>();
					csvBlock.add(data[i]);
					csvData.add(csvBlock);
				} else if(data[i].substring(0, 3).matches("900")){ //set trailer for each csv file
					csvTrailer = data[i];
				} else if(data[i].substring(0, 3).matches("300")){ //add 300 data
					csvData.get(csvBlockCount).add(data[i]);
				}
			}
		}
	}

	@Test
	@DisplayName("Check header is the 100 row")
	void testHeader() {
		assertEquals("100", csvHeader.substring(0,3));
	}
	
	@Test
	@DisplayName("Check trailer is the 900 row")
	void testTrailer() {
		assertEquals("900", csvTrailer.substring(0,3));
	}
	
	@Test
	@DisplayName("Check each csv block starts with a 200 row")
	void testCsvBlockStart(){
		for(int i = 0; i < csvData.size(); i++) {
			assertEquals("200", csvData.get(i).get(0).substring(0,3));
		}
	}
	

}
