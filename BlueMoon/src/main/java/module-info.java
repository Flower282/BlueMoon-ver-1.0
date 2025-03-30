module org.example.bluemoon {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.hibernate.orm.core;
    requires org.postgresql.jdbc;
    requires java.naming;
    requires jakarta.xml.bind;
    requires jakarta.persistence;
    requires de.mkammerer.argon2.nolibs;
    requires static lombok;


    // Mở package entity cho Hibernate
    exports org.example.bluemoon.controller;
    opens org.example.bluemoon.controller to javafx.fxml;
    exports org.example.bluemoon.application;
    opens org.example.bluemoon.models to org.hibernate.orm.core;
    opens org.example.bluemoon.models.enums to org.hibernate.orm.core;
    opens org.example.bluemoon.models.enumConverter to org.hibernate.orm.core;
}