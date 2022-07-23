module com.pablohdz.kafkatestui {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    
    requires validatorfx;
    requires kafka.clients;
    
    opens com.pablohdz.kafkatestui to javafx.fxml;
    exports com.pablohdz.kafkatestui;
}