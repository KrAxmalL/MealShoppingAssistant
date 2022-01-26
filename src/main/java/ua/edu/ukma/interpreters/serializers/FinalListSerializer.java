package ua.edu.ukma.interpreters.serializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ua.edu.ukma.interpreters.entities.FinalListProduct;
import ua.edu.ukma.interpreters.entities.PurchaseOrder;

public class FinalListSerializer extends StdSerializer<PurchaseOrder> {

	public FinalListSerializer() {
        this(null);
    }
  
    public FinalListSerializer(Class<PurchaseOrder> t) {
        super(t);
    }

	@Override
	public void serialize(PurchaseOrder value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		List<FinalListProduct> products = value.getFinalProductList();
		jgen.writeStartObject();
		jgen.writeFieldName("products");
		jgen.writeObject(products);
		double priceToGive = Math.round(value.getTotalPrice() * 100.0) / 100.0;
		jgen.writeNumberField("price", priceToGive);
		jgen.writeEndObject();
	}
}
