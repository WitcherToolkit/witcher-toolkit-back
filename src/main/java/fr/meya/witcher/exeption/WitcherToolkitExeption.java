package fr.meya.witcher.exeption;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class WitcherToolkitExeption extends RuntimeException {

	private final String messageKey; // Clé du message (optionnelle)
	private final Object[] messageArgs; // Arguments du message (optionnels)

	// Constructeur actuel (message brut uniquement)
	public WitcherToolkitExeption(String message) {
		super(message);
		this.messageKey = null;
		this.messageArgs = null;
	}

	// Nouveau constructeur : clé + arguments
	public WitcherToolkitExeption(String messageKey, Object... messageArgs) {
		super(messageKey); // Temporairement, sauf si résolu immédiatement
		this.messageKey = messageKey;
		this.messageArgs = messageArgs;
	}

	// Résolution depuis un MessageSource (formate le message final)
	public String resolveMessage(MessageSource messageSource) {
		if (messageSource == null) {
			return this.messageKey != null ? this.messageKey : super.getMessage();
		}
		return messageSource.getMessage(
				this.messageKey,
				this.messageArgs,
				this.messageKey, // Valeur par défaut si la clé est introuvable
				LocaleContextHolder.getLocale()
		);
	}

	public String getMessageKey() {
		return messageKey;
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}
}
