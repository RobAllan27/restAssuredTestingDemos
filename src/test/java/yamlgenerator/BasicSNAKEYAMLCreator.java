package yamlgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

//import com.esotericsoftware.yamlbeans.YamlReader;

public class BasicSNAKEYAMLCreator {

	public void createContactArray(){
		
		//reader = new YamlReader(new FileReader("C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools\\DX_Automation\\contacts.yaml"));
		   
		try {
		    InputStream input = new FileInputStream(new File("C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools\\DX_Automation\\contacts.yaml"));
		    Yaml yaml = new Yaml(new Constructor(Contact.class));
		    Contact contact = yaml.load(input);
		    //System.out.println(contact.name);
		    //System.out.println(contact.age);
		    //System.out.println(contact.address);
		    List<Phone> Phones =   contact.phonenumbers;
		    
		    Iterator<Phone> it = Phones.iterator();
		    while (it.hasNext()){
		    	Phone myphone = it.next();
		    	//System.out.println(myphone.name);
		    	//System.out.println(myphone.number);
		    	if ((myphone.name).equals("Home")){
		    		myphone.name = "HomeUpdated";
		    	}
		    	
		    	if ((myphone.number).equals("206-555-5138")){
		    		myphone.name = "206-555-3333";
		    	}
		    }
		    
		    /*
		    for (Phone phone:Phones){
		    	System.out.println(phone.name);
		    	//System.out.println(phone.number);
		    	
		    }
		    */
		    String output = yaml.dump(contact);
		    System.out.println(output);
		}
		catch (FileNotFoundException e){
			System.out.println(" Could not handle exception ");
		}	
	}
	
}
