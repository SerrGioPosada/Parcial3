module co.edu.uniquindio.poo.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.parcial3 to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial3.controller to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial3.model to javafx.base;

    exports co.edu.uniquindio.poo.parcial3;
    exports co.edu.uniquindio.poo.parcial3.controller;
    exports co.edu.uniquindio.poo.parcial3.model;
}