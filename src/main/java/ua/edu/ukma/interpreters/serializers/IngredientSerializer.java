package ua.edu.ukma.interpreters.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ua.edu.ukma.interpreters.entities.Ingredient;

public class IngredientSerializer extends StdSerializer<Ingredient>{

	public IngredientSerializer() {
        this(null);
    }
  
    public IngredientSerializer(Class<Ingredient> t) {
        super(t);
    }

    @Override
    public void serialize(
      Ingredient value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId().getProductId());
        jgen.writeNumberField("amount", value.getAmount());
        jgen.writeEndObject();
    }
}
