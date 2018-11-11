package yamlgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

//import com.esotericsoftware.yamlbeans.YamlReader;

public class BasicSNAKEYAMLCreator {

	public String createContactArray(){
		
		String returnedString = "";
		//reader = new YamlReader(new FileReader("C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools\\DX_Automation\\contacts.yaml"));
		   
		try {
		    InputStream input = new FileInputStream(new File("C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools\\DX_Automation\\contacts.yaml"));
		    Yaml yaml = new Yaml(new Constructor(Contact.class));
		    Contact contact = yaml.load(input);
		    List<Phone> Phones =   contact.phonenumbers;
		    Iterator<Phone> it = Phones.iterator();
		    while (it.hasNext()){
		    	Phone myphone = it.next();
		    	if ((myphone.name).equals("Home")){
		    		myphone.name = "HomeUpdated";
		    	}
		    	
		    	if ((myphone.number).equals("206-555-5138")){
		    		myphone.number = "206-555-3333";
		    	}
		    }
		    
		    String output = yaml.dump(contact);
	        DumperOptions options = new DumperOptions();
	        //options.setExplicitStart(true);
	        //options.setExplicitEnd(true);
	        options.setIndent(2);
	        options.setPrettyFlow(true);
	        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
	        //System.out.println("/n/n***********/n/n");
		    Yaml outputYAML = new Yaml(options);
		    String fullString = outputYAML.dump(contact); 
	        returnedString = fullString.substring(fullString.indexOf('\n')+1);
		    //returnedString = fullString;
		    System.out.println(returnedString);
		}
		catch (FileNotFoundException e){
			System.out.println(" Could not handle exception ");
		}
		return returnedString;
	}
	
}
