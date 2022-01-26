package ua.edu.ukma.interpreters.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ua.edu.ukma.interpreters.entities.FinalListProduct;
import ua.edu.ukma.interpreters.entities.Product;

public class FinalListProductSerializer extends StdSerializer<FinalListProduct> {
	
	public FinalListProductSerializer() {
        this(null);
    }
  
    public FinalListProductSerializer(Class<FinalListProduct> t) {
        super(t);
    }

	@Override
	public void serialize(FinalListProduct value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		Product product = value.getProduct();
		jgen.writeFieldName("product");
		jgen.writeObject(product);
		jgen.writeNumberField("packages", value.getPackageQuantity());
		double amountToGive = Math.round(value.getAmount() * 1000.0) / 1000.0;
		jgen.writeNumberField("amount", amountToGive);
		jgen.writeEndObject();
	}
}
