package com.github.leblancjs.vitogi;

import com.github.leblancjs.vitogi.l10n.VitogiLocalizations;
import com.github.leblancjs.vitogi.settings.SettingsPane;
import com.github.leblancjs.vitogi.settings.SettingsWindowShower;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Locale;

public class VitogiLauncher extends Application {
    private final VitogiLocalizations localizations = new VitogiLocalizations();

    @Override
    public void start(Stage primaryStage) {
        var locale = localizations.getLocale();

        var settingsPane = new SettingsPane(localizations, locale::set);
        settingsPane.localeProperty().bind(locale);
        settingsPane.setLocaleFieldItems(FXCollections.observableArrayList(Locale.ENGLISH, Locale.FRENCH));
        var settingsStageShower = new SettingsWindowShower(localizations, primaryStage, settingsPane);

        var settingsButton = new Button();
        settingsButton.textProperty().bind(localizations.get(VitogiLocalizations.Key.SETTINGS_TITLE));
        settingsButton.setOnAction(ignored -> settingsStageShower.show());

        primaryStage.titleProperty().bind(localizations.get(VitogiLocalizations.Key.APP_TITLE));
        primaryStage.setScene(new Scene(settingsButton));
        primaryStage.show();
    }
}
