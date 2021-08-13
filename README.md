# Gentrack-Assessment
 XML to CSV

1. Takes in the XML file as an input, converts it to a document and gets the tag named CSVIntervalData. Next it runs through the tag and gets the data blocks, then writes them out, using 100 as the header and 900 as the trailer.
2. 
3. To make it more extensible I would dynamically write the trailer instead of hardcoding the 900 in case there was more information after it
4. The code is java and can be ran in eclipse or other java IDE using just the java files and the xml input
