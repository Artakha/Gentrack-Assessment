package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlToCsv {
	
	public String csvHeader = "";
	
	public XmlToCsv() {
		
	}
	
	public void runConverter() throws Exception {
		XmlReader xmlReader = new XmlReader();
		Document xmlInput = xmlReader.getXmlInput();
		processData(xmlInput);
	}
	
	public void processData(Document xmlInput) throws IOException {
		ArrayList<String> csvData = new ArrayList<String>();
		Node node = xmlInput.getElementsByTagName("CSVIntervalData").item(0);
		Element eElement = (Element) node;
		String[] data = eElement.getTextContent().split("\n");		
		for(int i = 0; i < data.length; i++) {
			if(data[i].length() >= 3) {
				if(data[i].substring(0,3).matches("100")) {
					csvHeader = data[i];
				} else if(data[i].substring(0, 3).matches("200") || data[i].substring(0, 3).matches("900")) {
					if(!csvData.isEmpty()) {
						csvWriter(csvData);
						csvData.clear();
					}
					csvData.add(data[i]);
				} else {
					csvData.add(data[i]);
				}
			}
		}
	}
	
	public void csvWriter(ArrayList<String> input) throws IOException {
		String output = csvHeader + "\n";
		FileWriter writer=new FileWriter(input.get(0).split(",")[1] + ".csv");{
			for(int i = 0; i < input.size(); i++) {
				output = output.concat(input.get(i) + "\n");
			}
			output = output.concat("900");
			writer.write(output);
		}
		writer.close();
	}


}
