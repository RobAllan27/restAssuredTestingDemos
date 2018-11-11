package restAssuredAPIDemosPackage;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import componentsForJSONValues.Album;
import componentsForJSONValues.Artist;
import componentsForJSONValues.MediaCollection;
import componentsForJSONValues.Musician;

public class JSONMessageBuilder {

	private MediaCollection medcol;
	
	public void buildClassModel(String inArtist, String inRecordedCity){
		
		medcol = new  MediaCollection();
		medcol.setCollection("Rob's Collection");
		medcol.setCollection_type("music");
		
		Album alb1  = new Album();
		alb1.setTitle("Groovy Days");
		alb1.setLinks(new String[]{"link1","link2","link3"});
		alb1.setRecordedcity(inRecordedCity);
		alb1.setSongs( new String[] {"Happy Days", "Sunny Days", "Lovely Days"});
		
		// now lets create the artist and add them
		Artist artistA = new Artist();
		artistA.setName(inArtist);
		artistA.setBirthDate("-1344252310000");
		alb1.setArtist(artistA);
		
		//Now lets create the musicians and add them
		Musician musicianA1 = new Musician();
		Musician musicianA2 = new Musician();
		Musician musicianA3 = new Musician();
		musicianA1.setName("James Dean");
		musicianA2.setName("Brad Pitt");
		musicianA3.setName("Bruce Willis");
		musicianA1.setInstrument("Guitar");
		musicianA2.setInstrument("Guitar");
		musicianA3.setInstrument("Drums");
		Musician[] musicianSetA = new Musician[]{musicianA1,musicianA2,musicianA3};
		alb1.setMusician(musicianSetA);
		
		Album alb2  = new Album();
		alb2.setTitle("Happy Nights");
		alb2.setLinks(new String[]{"link11","link12"});
		alb2.setRecordedcity("Liverpool");
		alb2.setSongs( new String[] {"Happy Nights", "Sunny Nights", "Lovely Nights"});
		
		// now lets create the artist and add them
		Artist artistB = new Artist();
		artistB.setName("John Steinbeck");
		artistB.setBirthDate("-1344252310000");
		alb2.setArtist(artistB);
		
		//Now lets create the musicians and add them
		Musician musicianB1 = new Musician();
		Musician musicianB2 = new Musician();
		Musician musicianB3 = new Musician();
		musicianB1.setName("Leo Tolstoy");
		musicianB2.setName("Willian Shakespeare");
		musicianB3.setName("Charles Dickens");
		musicianB1.setInstrument("Guitar");
		musicianB2.setInstrument("Guitar");
		musicianB3.setInstrument("Drums");
		Musician[] musicianSetB = new Musician[]{musicianB1,musicianB2,musicianB3};
		alb2.setMusician(musicianSetB);
				
		Album alb3  = new Album();
		alb3.setTitle("Happy Nights");
		alb3.setLinks(new String[]{"link11","link12"});
		alb3.setRecordedcity("Liverpool");
		alb3.setSongs( new String[] {"Happy Nights", "Sunny Nights", "Lovely Nights"});
		
		// now lets create the artist and add them
		Artist artistC = new Artist();
		artistC.setName("John Steinbeck");
		artistC.setBirthDate("-1344252310000");
		alb3.setArtist(artistC);
		
		//Now lets create the musicians and add them
		Musician musicianC1 = new Musician();
		Musician musicianC2 = new Musician();
		Musician musicianC3 = new Musician();
		musicianC1.setName("Kevin Keegan");
		musicianC2.setName("Michael Owen");
		musicianC3.setName("Robbie Fowler");
		musicianC1.setInstrument("Guitar");
		musicianC2.setInstrument("Guitar");
		musicianC3.setInstrument("Drums");
		Musician[] musicianSetC = new Musician[]{musicianC1,musicianC2,musicianC3};
		alb3.setMusician(musicianSetC);
		
		medcol.setAlbum(new Album[]{alb1,alb2,alb3});
		
		medcol.setCollection("Rob's Collection");
		medcol.setCollection_type("music");
	}
	
	public String jsonString(){
		String JSONreturString = "";;
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, false);
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
		mapper.setDateFormat(outputFormat);

		mapper.setSerializationInclusion(Include.NON_EMPTY);
		try{
			JSONreturString =  mapper.writeValueAsString(medcol);
		} catch (Exception e)
		{
			System.out.println(e.getMessage() + " - there was hiccup error in JSON creation");
		}
		System.out.println("\n\n **** \n\n"+ JSONreturString + "\n\n **** \n\n");
		return JSONreturString;
	}
}