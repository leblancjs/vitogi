package com.github.leblancjs.vitogi.settings;

import com.github.leblancjs.vitogi.l10n.VitogiLocalizations;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingsWindowShower {
    private final Stage settingsStage;

    public SettingsWindowShower(VitogiLocalizations localizations, Stage primaryStage, SettingsPane settingsPane) {
        settingsStage = new Stage(StageStyle.UTILITY);
        settingsStage.initOwner(primaryStage);
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.titleProperty().bind(localizations.get(VitogiLocalizations.Key.SETTINGS_TITLE));
        settingsStage.setScene(new Scene(settingsPane));

        settingsPane.setOnAction(settingsStage::close);
    }

    public void show() {
        settingsStage.show();
    }
}
