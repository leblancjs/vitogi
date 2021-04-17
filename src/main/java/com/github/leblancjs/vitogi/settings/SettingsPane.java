package com.github.leblancjs.vitogi.settings;

import com.github.leblancjs.vitogi.l10n.VitogiLocalizations;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class SettingsPane extends VBox {
    private final Label localeFieldTitleLabel;
    private final ComboBox<Locale> localeFieldComboBox;
    private final VBox localeFieldPane;
    private final Button okButton;
    private final Button cancelButton;
    private final HBox buttonsPane;
    private final Consumer<Locale> localeUpdater;
    private final ObjectProperty<Locale> locale;
    private final ObjectProperty<Runnable> onActionProperty;

    public SettingsPane(VitogiLocalizations localizations, Consumer<Locale> localeUpdater) {
        this.localeUpdater = requireNonNull(localeUpdater);
        locale = new SimpleObjectProperty<>();
        onActionProperty = new SimpleObjectProperty<>();

        localeFieldTitleLabel = new Label();
        localeFieldTitleLabel.textProperty().bind(localizations.get(VitogiLocalizations.Key.SETTINGS_LOCALE_FIELD_TITLE));
        localeFieldComboBox = new ComboBox<>();
        localeFieldComboBox.promptTextProperty().bind(localizations.get(VitogiLocalizations.Key.SETTINGS_LOCALE_FIELD_PROMPT));
        localeFieldComboBox.setButtonCell(new LocaleListCell(locale));
        localeFieldComboBox.setCellFactory(ignored -> new LocaleListCell(locale));
        localeFieldPane = new VBox(localeFieldTitleLabel, localeFieldComboBox);

        locale.addListener((ignored, oldLocale, newLocale) -> localeFieldComboBox.getSelectionModel().select(newLocale));

        okButton = new Button();
        okButton.textProperty().bind(localizations.get(VitogiLocalizations.Key.SETTINGS_OK_BUTTON_TEXT));
        okButton.setOnAction(
                ignored -> {
                    this.localeUpdater.accept(localeFieldComboBox.getValue());
                    Optional.ofNullable(onActionProperty.get()).ifPresent(Runnable::run);
                }
        );
        cancelButton = new Button();
        cancelButton.textProperty().bind(localizations.get(VitogiLocalizations.Key.SETTINGS_CANCEL_BUTTON_TEXT));
        cancelButton.setOnAction(ignored -> Optional.ofNullable(onActionProperty.getValue()).ifPresent(Runnable::run));
        buttonsPane = new HBox(okButton, cancelButton);

        getChildren().setAll(localeFieldPane, buttonsPane);
    }

    public void setLocaleFieldItems(ObservableList<Locale> locales) {
        localeFieldComboBox.itemsProperty().set(locales);
    }

    public ObjectProperty<ObservableList<Locale>> localeFieldItemsProperty() {
        return localeFieldComboBox.itemsProperty();
    }

    public ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    public void setOnAction(Runnable onAction) {
        onActionProperty.set(onAction);
    }

    public ObjectProperty<Runnable> onActionProperty() {
        return onActionProperty;
    }
}
