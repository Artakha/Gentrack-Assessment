package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlToCsv {
	
	public String csvHeader = "";
	public String csvTrailer = "";
	public int arrayListCount = -1;
	
	public XmlToCsv() {
		
	}
	
	public void runConverter() throws Exception {
		XmlReader xmlReader = new XmlReader();
		Document xmlInput = xmlReader.getXmlInput();
		processData(xmlInput);
	}
	
	public void processData(Document xmlInput) throws IOException {
		ArrayList<ArrayList<String>> csvData = new ArrayList<ArrayList<String>>(1);
		Node node = xmlInput.getElementsByTagName("CSVIntervalData").item(0);
		Element eElement = (Element) node;
		String[] data = eElement.getTextContent().split("\n");		
		for(int i = 0; i < data.length; i++) {
			if(data[i].length() >= 3) {
				if(data[i].substring(0,3).matches("100")) { //set header for each csv file
					csvHeader = data[i];
				} else if(data[i].substring(0, 3).matches("200")) { //create new block for a new csv file
					arrayListCount++; 
					ArrayList<String> csvBlock = new ArrayList<String>();
					csvBlock.add(data[i]);
					csvData.add(csvBlock);
				} else if(data[i].substring(0, 3).matches("900")){ //set trailer for each csv file
					csvTrailer = data[i];
				} else if(data[i].substring(0, 3).matches("300")){ //add 300 data
					csvData.get(arrayListCount).add(data[i]);
				}
			}
		}
		for(int i = 0; i < csvData.size(); i++) {
			csvWriter(csvData.get(i)); //write all blocks to csv
		}
	}
	
	public void csvWriter(ArrayList<String> input) throws IOException {
		String output = csvHeader + "\n";
		FileWriter writer=new FileWriter(input.get(0).split(",")[1] + ".csv");{
			for(int i = 0; i < input.size(); i++) {
				output = output.concat(input.get(i) + "\n");
			}
			output = output.concat(csvTrailer);
			writer.write(output);
		}
		writer.close();
	}


}
