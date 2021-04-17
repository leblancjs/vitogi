module com.github.leblancjs.vitogi {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;

    opens com.github.leblancjs.vitogi to javafx.graphics;
}