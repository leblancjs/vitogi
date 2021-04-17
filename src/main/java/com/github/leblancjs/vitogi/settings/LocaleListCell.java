package com.github.leblancjs.vitogi.settings;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.ListCell;

import java.util.Locale;

import static java.util.Objects.requireNonNull;
import static javafx.beans.binding.Bindings.createStringBinding;

public class LocaleListCell extends ListCell<Locale> {
    private final ReadOnlyObjectProperty<Locale> locale;

    public LocaleListCell(ReadOnlyObjectProperty<Locale> locale) {
        this.locale = requireNonNull(locale);
    }

    @Override
    protected void updateItem(Locale item, boolean empty) {
        super.updateItem(item, empty);

        setGraphic(null);

        if (item == null || empty) {
            textProperty().unbind();
            setText(null);
        } else {
            textProperty().bind(createStringBinding(() -> capitalizeFirstLetter(item.getDisplayName(locale.get())), locale));
        }
    }

    private String capitalizeFirstLetter(String word) {
        if (word.isBlank())
            return word;

        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
