package fr.meya.witcher.common.utils;

public class ValidationRule {
    private final String message;       // Message d'erreur explicite
    private final String messageKey;    // Clé du message externalisé
    private final Integer maxLength;    // Longueur maximale

    // Constructeur avec message explicite
    public ValidationRule(String message, Integer maxLength) {
        this.message = message;
        this.messageKey = null;
        this.maxLength = maxLength;
    }

    // Constructeur avec clé de message
    public ValidationRule(String messageKey) {
        this.messageKey = messageKey;
        this.message = null;
        this.maxLength = null;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Integer getMaxLength() {
        return maxLength;
    }
}