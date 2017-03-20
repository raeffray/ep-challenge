package raeffray.ep.challenge.maze.helper;

import java.io.IOException;


import java.io.StringWriter;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JSONBuilder {
	
	public static String getJSON(List<? extends Transformable> object){
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();

		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
	    
	    mapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
	    mapper.getSerializationConfig().setAnnotationIntrospector(introspector);
	    
		try {
			mapper.writeValue(writer , object);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return writer.toString();
	}
	
	public static Object getObject(String json,Class<? extends Transformable> clazz){		
	
		ObjectMapper mapper = new ObjectMapper();
		
		Object object = null;
		
		try {
			
			
			
			object = mapper.readValue(json, clazz);
			
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return object;
	}
	
	
}