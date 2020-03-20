package database.utilities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PriceConverter implements AttributeConverter<Price, String> {
    @Override
    public String convertToDatabaseColumn(Price price) {

        return String.format("%.2f$", price.getPrice());
    }

    @Override
    public Price convertToEntityAttribute(String s) {
        if(s == null){
//            throw new NullPointerException("Failed to convert null to UserAddress");
            return null;
        }


        return new Price(Double.parseDouble(s.replace("$", "").replace(",", ".")));
    }
}
