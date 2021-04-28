package com.github.leblancjs.vitogi.l10n

import javafx.beans.binding.Bindings
import javafx.beans.binding.StringBinding
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import java.util.*

class VitogiLocalizations {
    enum class Key(val value: String) {
        APP_TITLE("app.title"),
        SETTINGS_TITLE("settings.title"),
        SETTINGS_LOCALE_FIELD_TITLE("settings.locale_field.title"),
        SETTINGS_LOCALE_FIELD_PROMPT("settings.locale_field.prompt"),
        SETTINGS_OK_BUTTON_TEXT("settings.ok_button.text"),
        SETTINGS_CANCEL_BUTTON_TEXT("settings.cancel_button.text"),
        SETTINGS_LOCALE_LIST_CELL_ENGLISH_TEXT("settings.locale_list_cell.english.text"),
        SETTINGS_LOCALE_LIST_CELL_FRENCH_TEXT("settings.locale_list_cell.french.text")
    }

    val locale: ObjectProperty<Locale> = SimpleObjectProperty()
    private val bundle: ObjectProperty<ResourceBundle> = SimpleObjectProperty()

    init {
        locale.addListener { _, _, newLocale -> bundle.set(getBundle(newLocale)) }
        locale.set(Locale.ENGLISH)
    }

    fun get(key: Key): StringBinding {
        return Bindings.createStringBinding({ bundle.get().getString(key.value) }, bundle)
    }

    private fun getBundle(locale: Locale): ResourceBundle {
        return ResourceBundle.getBundle(VitogiLocalizations::class.qualifiedName!!, locale)
    }
}