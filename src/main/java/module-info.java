module net.jorjai.bummer4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens net.jorjai.bummer4 to javafx.fxml;
    exports net.jorjai.bummer4;
}