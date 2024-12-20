package fr.meya.witcher.common.utils;

import fr.meya.witcher.exeption.WitcherToolkitExeption;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Map;

public class ValidationUtils {

    private final MessageSource messageSource; // Injection de MessageSource

    public ValidationUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public <T> void validateWithRules(T object, Map<String, ValidationRule> fieldRules) {
        if (object == null) {
            throw new WitcherToolkitExeption(resolveMessage("error.validation.object.null"));
        }

        for (Map.Entry<String, ValidationRule> entry : fieldRules.entrySet()) {
            String fieldName = entry.getKey();
            ValidationRule rule = entry.getValue();

            try {
                // Obtenir la valeur du champ
                Object fieldValue = object.getClass()
                        .getMethod("get" + capitalize(fieldName))
                        .invoke(object);

                if (fieldValue == null || (fieldValue instanceof String && ((String) fieldValue).isBlank())) {
                    throw new WitcherToolkitExeption(
                            rule.getMessageKey(),
                            fieldName // Paramètre dynamique à insérer dans le message
                    );
                }

            } catch (NoSuchMethodException e) {
                throw new WitcherToolkitExeption("error.validation.field.invalid", fieldName);
            } catch (Exception e) {
                throw new WitcherToolkitExeption("error.validation.generic", fieldName);
            }
        }
    }

    private String resolveMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
