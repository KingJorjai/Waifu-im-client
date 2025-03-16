module net.jorjai.bummer4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;

    opens net.jorjai.bummer4.domain to com.google.gson;
    opens net.jorjai.bummer4.presentation to javafx.fxml;
    exports net.jorjai.bummer4.presentation;
}