module com.example.testopen {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.testopen to javafx.fxml;
    exports com.example.testopen;
}