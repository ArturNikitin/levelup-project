package database.utilities;

import database.entities.User;
import database.utilities.UserAddress;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserAddressConverter implements AttributeConverter<UserAddress, String> {

    @Override
    public String convertToDatabaseColumn(UserAddress userAddress) {
        if (userAddress == null){
            return null;
        }
        return String.format("%s/%s/%s/%s", userAddress.getCountry(), userAddress.getCity(), userAddress.getStreet(), userAddress.getPostCode());
    }

    @Override
    public UserAddress convertToEntityAttribute(String s) {
        if(s == null){
//            throw new NullPointerException("Failed to convert null to UserAddress");
            return null;
        }

        String[] address = s.split("/");

        if(address.length != 4){
            throw new IllegalArgumentException("Failed to convert " + s + " to UserAddress");
        }
        return new UserAddress(address[0], address[1], address[2], address[3]);
    }
}
