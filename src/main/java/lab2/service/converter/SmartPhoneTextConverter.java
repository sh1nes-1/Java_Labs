package lab2.service.converter;

import lab2.model.SmartPhone;
import lab2.exception.ConvertException;
import lab2.service.Converter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartPhoneTextConverter implements Converter<SmartPhone> {

    private final String FIELDS_SEPARATOR = "-";
    private final Integer FIELDS_COUNT = 7;

    private Object[] getSmartPhoneFields(SmartPhone smartPhone) {
        return new Object[] {
            smartPhone.getId(), smartPhone.getName(), smartPhone.getColor(), smartPhone.getDiagonal(), smartPhone.getRam(),
            smartPhone.getPrice(), smartPhone.getReleaseDate()
        };
    }

    @Override
    public String serializeToString(SmartPhone smartPhone) throws ConvertException {
        try {
            Object[] smartPhoneFields = getSmartPhoneFields(smartPhone);

            List<String> stringFields = Arrays.stream(smartPhoneFields)
                    .map(Object::toString)
                    .map(o -> o.replace(FIELDS_SEPARATOR, "\\" + FIELDS_SEPARATOR))
                    .collect(Collectors.toList());

            return String.join(FIELDS_SEPARATOR, stringFields);
        }
        catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    @Override
    public SmartPhone deserializeString(String serializedString) throws ConvertException {
        try {
            String[] stringFields = serializedString.split("(?<!\\\\)" + FIELDS_SEPARATOR);

            if (stringFields.length != FIELDS_COUNT) {
                throw new Exception("Invalid format of string!");
            }

            Iterator<String> fieldsIterator = Arrays.stream(stringFields).map(s -> s.replace("\\" + FIELDS_SEPARATOR, FIELDS_SEPARATOR)).iterator();

            return new SmartPhone.Builder()
                    .setId(Integer.parseInt(fieldsIterator.next()))
                    .setName(fieldsIterator.next())
                    .setColor(SmartPhone.Color.valueOf(fieldsIterator.next()))
                    .setDiagonal(Double.parseDouble(fieldsIterator.next()))
                    .setRam(Integer.parseInt(fieldsIterator.next()))
                    .setPrice(Integer.parseInt(fieldsIterator.next()))
                    .setReleaseDate(LocalDate.parse(fieldsIterator.next()))
                    .build();
        }
        catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

}
