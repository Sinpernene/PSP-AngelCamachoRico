module com.ac2425.da.clientui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.ac2425.da.clientui to javafx.fxml;
    exports com.ac2425.da.clientui;
}