package dataProvider;

import restAssuredAPIDemosPackage.JSONMessageBuilder;
import yamlgenerator.BasicSNAKEYAMLCreator;
import yamlgenerator.BasicYAMLCreator;

public class DataProviderRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*basic JSON builder
		JSONMessageBuilder jsonMsgBldr = new JSONMessageBuilder();
		jsonMsgBldr.buildClassModel("artist","runner");
		jsonMsgBldr.jsonString();
		*/
		
		/*
		 * YAMLreader using YAMLBEANs
		BasicYAMLCreator bYAMLcreator= new BasicYAMLCreator();
		bYAMLcreator.importAdaptandChangeYAMLFile();
		*/
		
		
		BasicSNAKEYAMLCreator bsyc = new BasicSNAKEYAMLCreator();	
		bsyc.createContactArray();
		/*
		try{
		Object[][] testObjArray = ExcelFileReader.getTableArray("C:/Users/Rob/Userdata/DX Software Testing/Tools/set_of_test_data_for_automation.xlsx","RESTpathstobetested");	
		} catch (Exception e)
		{
			System.out.println("We got an error");
		}
		*/
		/*
		the set of pages are
		 - RESTpathstobetested
		 - RESTJSONMessageContents
		 - URLLoginsToBeTested
		*/
		
		/*
		C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools
		
		C:/Users/Rob/Userdata/DX Software Testing/Tools
		
		C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools
		
		*/
	}

}
