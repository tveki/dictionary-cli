package org.tveki.dictionary.cli.command;

import org.tveki.dictionary.api.Language;

import java.util.Objects;

public abstract class AbstractCommandExecutor implements CommandExecutor {

    public static final Language DEFAULT_FROM_LANGUAGE = Language.ENGLISH;
    public static final Language DEFAULT_TO_LANGUAGE = Language.HUNGARIAN;

    protected final Language fromLanguage;
    protected final Language toLanguage;

    public AbstractCommandExecutor(Language fromLanguage, Language toLanguage) {
        Objects.requireNonNull(fromLanguage, "fromLanguage cannot be null");
        Objects.requireNonNull(toLanguage, "toLanguage cannot be null");
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
    }

    public AbstractCommandExecutor() {
        this(DEFAULT_FROM_LANGUAGE, DEFAULT_TO_LANGUAGE);
    }


}
