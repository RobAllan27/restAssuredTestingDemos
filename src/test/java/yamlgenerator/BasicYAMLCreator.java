package yamlgenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;

//import java.util.Map;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

public class BasicYAMLCreator {

	public void importAdaptandChangeYAMLFile(){		
		LinkedHashMap map;
		try{
			
		// let's try using the class definitions.	
			
		YamlReader reader = new YamlReader(new FileReader("C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools\\DX_Automation\\contacts.yaml"));	
		Contact contact = reader.read(Contact.class);
		System.out.println(contact.age);
		
		System.out.println("\n\n\n\n");
		
		reader = new YamlReader(new FileReader("C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools\\DX_Automation\\contacts.yaml"));
	    Object object = reader.read();
	    System.out.println(object);
	    map = (LinkedHashMap)object;
	    // Now lets find out about the map
	    System.out.println(map.getClass().getName());
	    int j =0;
	    
	    // 1 - OK lets first print the array
	    // 2 - then we will update it
	    // 3 - then we will print it out again
	    
	    ArrayList arrayListFound;
	    for (Object objectMapEntry: map.values()){
	    	j++;
	    	System.out.println("Here is the map of objects in the top" + objectMapEntry.getClass().getName());
	    	if (j == 4){
	    		arrayListFound = (ArrayList)objectMapEntry;
	    		printIterateArounf2ndLevelAL(arrayListFound);
	    	}	
	    }
	    
	    j = 0;
	    System.out.println("\n\nNow updating the document");
	    for (Object objectMapEntry: map.values()){
	    	j++;
	    	if (j == 4){
	    		arrayListFound = (ArrayList)objectMapEntry;
	    		updateIterateArounf2ndLevelAL(arrayListFound);
	    	}	
	    }
	    
	    j = 0;
	    System.out.println("\n\nNow reprinting");
	    for (Object objectMapEntry: map.values()){
	    	j++;
	    	System.out.println("Here is the map of objects in the top" + objectMapEntry.getClass().getName());
	    	if (j == 4){
	    		arrayListFound = (ArrayList)objectMapEntry;
	    		printIterateArounf2ndLevelAL(arrayListFound);
	    	}	
	    }
	    
	    // Now lets output the results
	    YamlWriter writer = new YamlWriter(new FileWriter("C:\\Users\\Rob\\Userdata\\DX Software Testing\\Tools\\DX_Automation\\outputContacts.yaml"));
	    //writer.getConfig().setPropertyElementType(type, propertyName, elementType);
	    writer.write(map);
	    writer.close();
	        
		} catch (Exception e){	
			System.out.println(e.getMessage());
		}	
	}
	
    private void printIterateArounf2ndLevelAL(ArrayList arrayListFound){
    	for (Object objectALMapEntry: arrayListFound){
    	getContentsof2ndArrayListMember((LinkedHashMap)objectALMapEntry);
    	}
    }
    
    private void updateIterateArounf2ndLevelAL(ArrayList arrayListFound){
    	int chooseWhichArrayListToChange = 1;
    	int currentArray = 1;
    	for (Object objectALMapEntry: arrayListFound){
    	if (chooseWhichArrayListToChange == currentArray)
    	{
    		purgeAndResetValues((LinkedHashMap)objectALMapEntry);
    	}
    	currentArray++;
    	}
    }
    

    private void purgeAndResetValues(LinkedHashMap linkHashMap){	
    	int i = 0;
    	for( Object key : linkHashMap.keySet() ){
 
    		System.out.println(linkHashMap.get(key));
    		if (i == 0){
    		linkHashMap.replace(key,"1st newvalue");
    		  // ...
    		}
    		
    		if (i == 1){
    		linkHashMap.replace(key,"2nd newvalue");
    		  // ...
    		}
    		
    		i++;
    		}
    }
    
    private void getContentsof2ndArrayListMember(LinkedHashMap linkHashMap){
    	System.out.println("Getting the contents of the linkHashMap");
    	int hcounter = 0;
    	for (Object objectLHMapEntry: linkHashMap.values()){
    	System.out.println("Here is the map in the 2nd layer arraylist " + objectLHMapEntry.getClass().getName() + " " + objectLHMapEntry.toString());
    }
    }
   /* 
    public class Contact {
    	public String name;
    	public int age;
    	
    	public List<Phone> phoneNumbers;
    }

    public class Phone {
    	public String name;
    	public String number;
    }
    */
 }
