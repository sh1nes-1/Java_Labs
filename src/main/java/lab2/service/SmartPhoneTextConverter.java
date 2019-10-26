package lab2.service;

import lab2.model.SmartPhone;
import lab2.exception.ConvertException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SmartPhoneTextConverter implements Converter<SmartPhone> {

    private final String FIELDS_SEPARATOR = "-";

    private Object[] getSmartPhoneFields(SmartPhone smartPhone) {
        return new Object[] {
            smartPhone.getName(), smartPhone.getColor(), smartPhone.getDiagonal(), smartPhone.getRam(),
            smartPhone.getPrice(), smartPhone.getReleaseDate()
        };
    }

    @Override
    public String serializeToString(SmartPhone smartPhone) throws ConvertException {
        try {
            Object[] smartPhoneFields = getSmartPhoneFields(smartPhone);
            List<String> stringFields = Arrays.stream(smartPhoneFields).map(Object::toString).map(o -> o.replace(FIELDS_SEPARATOR, "\\" + FIELDS_SEPARATOR)).collect(Collectors.toList());
            return String.join(FIELDS_SEPARATOR, stringFields);
        }
        catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    @Override
    public SmartPhone deserializeString(String serializedString) throws ConvertException {
        try {
            if (!serializedString.contains(FIELDS_SEPARATOR)) {
                throw new Exception("Invalid format!");
            }

            String[] stringFields = serializedString.split("(?<!\\\\)" + FIELDS_SEPARATOR);
            stringFields = Arrays.stream(stringFields).map(s -> s.replace("\\" + FIELDS_SEPARATOR, FIELDS_SEPARATOR)).toArray(String[]::new);

            return new SmartPhone.Builder()
                    .setName(stringFields[0])
                    .setColor(SmartPhone.Color.valueOf(stringFields[1]))
                    .setDiagonal(Double.parseDouble(stringFields[2]))
                    .setRam(Integer.parseInt(stringFields[3]))
                    .setPrice(Integer.parseInt(stringFields[4]))
                    .setReleaseDate(LocalDate.parse(stringFields[5]))
                    .build();
        }
        catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

}
