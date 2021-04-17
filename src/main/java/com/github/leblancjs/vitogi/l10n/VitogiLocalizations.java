package com.github.leblancjs.vitogi.l10n;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class VitogiLocalizations {
    public enum Key {
        APP_TITLE("app.title"),
        SETTINGS_TITLE("settings.title"),
        SETTINGS_LOCALE_FIELD_TITLE("settings.locale_field.title"),
        SETTINGS_LOCALE_FIELD_PROMPT("settings.locale_field.prompt"),
        SETTINGS_OK_BUTTON_TEXT("settings.ok_button.text"),
        SETTINGS_CANCEL_BUTTON_TEXT("settings.cancel_button.text"),
        SETTINGS_LOCALE_LIST_CELL_ENGLISH_TEXT("settings.locale_list_cell.english.text"),
        SETTINGS_LOCALE_LIST_CELL_FRENCH_TEXT("settings.locale_list_cell.french.text");

        private final String value;

        Key(String value) {
            this.value = requireNonNull(value);
        }

        public String getValue() {
            return value;
        }
    }

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(VitogiLocalizations.class.getName());

    private final ObjectProperty<Locale> locale;
    private final ObjectProperty<ResourceBundle> bundle;

    public VitogiLocalizations() {
        bundle = new SimpleObjectProperty<>();

        locale = new SimpleObjectProperty<>();
        locale.addListener((ignored, oldLocale, newLocale) -> bundle.set(getBundle(newLocale)));
        locale.set(Locale.ENGLISH);
    }

    public StringBinding get(Key key) {
        return Bindings.createStringBinding(() -> bundle.get().getString(key.getValue()), bundle);
    }

    public ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    private ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle(VitogiLocalizations.class.getName(), locale);
    }
}
