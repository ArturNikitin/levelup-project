package database.utilities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter
public class DateConverter implements AttributeConverter<Date, Long> {
    @Override
    public Long convertToDatabaseColumn(Date date) {
        return date.getTime();
    }

    @Override
    public Date convertToEntityAttribute(Long aLong) {
        if (aLong == null){
            return null;
        }
        return new Date(aLong);
    }
}
