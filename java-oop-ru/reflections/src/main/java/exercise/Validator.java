package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();
        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull notNullFiled = field.getAnnotation(NotNull.class);
            field.setAccessible(true);
            try {
                    if (notNullFiled != null) {
                        if (field.get(address) == null) {
                            result.add(field.getName());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new HashMap<>();
        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull notNull = field.getDeclaredAnnotation(NotNull.class);
            MinLength minLength = field.getDeclaredAnnotation(MinLength.class);
            field.setAccessible(true);
            try {
                if (notNull != null) {
                    if (field.get(address) == null) {
                        result.put(field.getName(), List.of("can not be null"));
                    }
                }
                if (minLength != null) {
                    if (field.get(address).toString().length() < minLength.minLength()) {
                        result.put(field.getName(), List.of("length less than " + minLength.minLength()));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
// END
