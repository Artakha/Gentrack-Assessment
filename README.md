# Gentrack-Assessment
 XML to CSV

1. Takes in the XML file as an input, converts it to a document in java and gets the tag named CSVIntervalData. Next it runs through the tag and gets the csv data blocks, then writes each of them out to a separate file, using 100 as the header and 900 as the trailer.
2. More tests and checks to check incoming data is valid
3. I would separate the XmlToCsv class out more to make it easier to test, and add more tests and checks to see that the incoming XML file is valid
4. The code is java and can be ran in eclipse or other java IDE using just the java files under the main folder/package and the xml input file, then running it from the Main class, the tests are jUnit and can also be run in eclipse from the DataProcessingTest.java in the test folder/package
