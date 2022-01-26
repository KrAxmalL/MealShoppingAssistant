package ua.edu.ukma.interpreters.serializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ua.edu.ukma.interpreters.entities.Dish;
import ua.edu.ukma.interpreters.entities.DishLabel;
import ua.edu.ukma.interpreters.entities.Ingredient;

public class DishSerializer extends StdSerializer<Dish> {

	public DishSerializer() {
        this(null);
    }
  
    public DishSerializer(Class<Dish> t) {
        super(t);
    }

    @Override
    public void serialize(
      Dish value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("title", value.getTitle());
        jgen.writeStringField("description", value.getDescription());
        jgen.writeStringField("src", value.getSrc());
        jgen.writeNumberField("portions", value.getPortions());
        DishLabel label = value.getLabel();
        if(label == null) {
        	jgen.writeStringField("labelId", null);
        }
        
        else {
        	jgen.writeNumberField("labelId", label.getId());
        }
        jgen.writeFieldName("products");
        //jgen.writeStartArray();
        List<Ingredient> ingredients = value.getProducts();
        jgen.writeObject(ingredients);
        /*for(Ingredient ingredient: ingredients) {
        	jgen.writeObject(ingredient);
        	jgen.write
        }*/
        jgen.writeEndObject();
    }
}
