package application;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javafx.scene.control.Label;

public class OnMessageCallback implements MqttCallback{
	Label label = new Label("Label");
	public void connectionLost(Throwable cause) {
		System.out.println("Disconnect, you can reconnect");
		
	}
	
	public void messageArrived(String topic, MqttMessage message) throws Exception{
		
		System.out.println("Recieved message topic: " + topic);
		System.out.println("Recieved message Qos: " + message.getQos());
		System.out.println("Recieved message content: " + new String(message.getPayload()) );
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}