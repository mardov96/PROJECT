module Borrador {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires org.eclipse.paho.client.mqttv3;
	
	opens application to javafx.graphics, javafx.fxml;
}
