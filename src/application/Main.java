package application;
	
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	String subTopic;
	String pubTopic = "testtopic/1";
    String content = "Hello World";
    int qos = 2;
    String broker = "tcp://localhost:1883";
    String clientId = "user1";
    Met_images metimagen = new Met_images();
    MemoryPersistence persistence = new MemoryPersistence();
    
    
	@Override
	public void start(Stage escenario) throws Exception {
		
        
		
		TextField user = new TextField();
		PasswordField clave = new PasswordField();
		Button login = new Button("Iniciar sesión");
		Label titulo = new Label("");
		
		
		
	    // estableciendo formato del login
		user.setFont(Font.font("Comic Sans Ms", FontWeight.LIGHT, 14));
		user.setPromptText("Usuario");
		user.setFocusTraversable(false);
		user.setMaxWidth(180);
		
		clave.setFont(Font.font("Comic Sans Ms", FontWeight.LIGHT, 14));
		clave.setPromptText("Contraseña");
		clave.setFocusTraversable(false);
		clave.setMaxWidth(180);
		
		login.setFont(Font.font("Comic Sans Ms", FontWeight.NORMAL, 14));
		login.setMinHeight(30);
		
		titulo.setFont(Font.font("Comic Sans Ms", FontWeight.EXTRA_BOLD, 18));
		titulo.setStyle("-fx-text-fill: blue");
		
		
		//boton login
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				// implementando mqtt
				
				subTopic = user.getText();
				
				try {
		        	 MqttClient client = new MqttClient(broker, clientId, persistence);

		            // MQTT connection option
		            MqttConnectOptions connOpts = new MqttConnectOptions();
		            //connOpts.setUserName("emqx_test");
		            //connOpts.setPassword("emqx_test_password".toCharArray());
		            // retain session
		            connOpts.setCleanSession(true);

		            // set callback
		            //client.setCallback(new OnMessageCallback());
		            

		            // establish a connection
		            System.out.println("Connecting to broker: " + broker);
		            client.connect(connOpts);

		            System.out.println("Connected");
		            //System.out.println("Publishing message: " + content.toString());
		            
		            client.setCallback(new MqttCallback() {
						
						@Override
						public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
							String mensaje = arg1.toString();
							System.out.println(mensaje);
							//txtF2.setText(mensaje);
							
						}
						
						@Override
						public void deliveryComplete(IMqttDeliveryToken arg0) {
							System.out.println("deliveryComplete---------" + arg0.isComplete());
							
						}
						
						@Override
						public void connectionLost(Throwable arg0) {
							// TODO Auto-generated method stub
							
						}
					});


		            // Subscribe
		            client.subscribe(subTopic);

		            // Required parameters for message publishing
		            //MqttMessage message = new MqttMessage(content.getBytes());
		            //message.setQos(qos);
		            //client.publish(pubTopic, message);
		            //System.out.println("Message published"); 
		           
		        }
				catch (MqttException me) {
		        	System.out.println ("x" + me.getReasonCode());
		        	System.out.println("msg " + me.getMessage());
		        	System.out.println("loc " + me.getLocalizedMessage());
		        	System.out.println("cause " + me.getCause());
		        	System.out.println("cause " + me.getCause());
		        	System.out.println("excep " + me);
		        	
		            System.out.println("reason " + me.getReasonCode());
		            System.out.println("msg " + me.getMessage());
		            System.out.println("loc " + me.getLocalizedMessage());
		            System.out.println("cause " + me.getCause());
		            System.out.println("excep " + me);
		            me.printStackTrace();
		        }

				
				
				
				escenario.close();
				TextField chat = new TextField();
				Button enviar = new Button("Enviar");
				Label globo_msg = new Label();
				
				chat.setFont(Font.font("Comic Sans Ms", FontWeight.NORMAL, 14));
				chat.setMinSize(500, 10);
				
				enviar.setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, 14));
				enviar.setMinSize(20, 10);
				
				globo_msg.setFont(Font.font("Comic Sans Ms", FontWeight.LIGHT, 14));
				
				VBox contenidoIzq = new VBox(15);
				
				for(int i = 0; i < 10; i++) {
					Image foto = new Image("file:anime.jpg");
					
					//metimagen.circuloimage(foto);
					
					
					GridPane usuarios = new GridPane();
					Label usuarioId = new Label("Usuario "+(i+1));
					Label mensajeUser = new Label("Hola como estas amigo");
					Label hora = new Label("08:30");
					
					usuarios.add(usuarioId, 0, 0);
					usuarios.add(mensajeUser, 0, 1);
					usuarios.add(hora, 1, 0);

					usuarios.setVgap(10);
					usuarios.setHgap(10);
					
					FlowPane contUser = new FlowPane(metimagen.circuloimage(foto), usuarios);
					contUser.setHgap(15);
					contUser.setPadding(new Insets(10, 0, 0, 10));
					
					contenidoIzq.getChildren().add(contUser);
					
				}
				
				ScrollPane panelizq = new ScrollPane();
				panelizq.setContent(contenidoIzq);
				panelizq.setFitToWidth(true);
				panelizq.setPrefWidth(320);				
				panelizq.setHbarPolicy(ScrollBarPolicy.NEVER);
				panelizq.setPannable(true);
				
				FlowPane contenido = new FlowPane();
				
				// mensaje repetido
				
				for(int i = 0; i < 10; i++) {
					Image foto = new Image("file:anime.jpg");
					
					
					HBox mensajes = new HBox(20);
					
					Label lb = new Label("Hola como estas bienvenido al programa "+(i+1));
					lb.setTextAlignment(TextAlignment.JUSTIFY);
					lb.setWrapText(true);
					lb.setMaxWidth(200);
					lb.setStyle("-fx-background-color: rgb(179,231,244); -fx-background-radius: 8px;");
					lb.setPadding(new Insets(6));
					mensajes.getChildren().addAll(metimagen.circuloimage(foto), lb);
					
					mensajes.setPrefWidth(555);
					mensajes.setPadding(new Insets(10, 0, 10, 10));
					contenido.getChildren().add(mensajes);
					
					
				}
				
				
				
				
				ScrollPane panelder = new ScrollPane();
				panelder.setContent(contenido);
				panelder.setFitToWidth(true);
				panelder.setHbarPolicy(ScrollBarPolicy.NEVER);
				panelder.setPrefWidth(570);
				panelder.setPannable(true);
				
				
				
				// m�todo que al dar enter se env�e el mensaje
				chat.setOnKeyPressed(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent Event) {
						if (Event.getCode() == KeyCode.ENTER)  {

					    	Image foto = new Image("file:anime.jpg");
							
							HBox mensajes = new HBox(20);
							
							Label lb = new Label();
							lb.setText(chat.getText());// se setea y envia msg
							lb.setTextAlignment(TextAlignment.LEFT);
							lb.setWrapText(true);
							lb.setMaxWidth(200);
							lb.setStyle("-fx-background-color: rgb(179,231,244); -fx-background-radius: 8px;");
							lb.setPadding(new Insets(6));
							mensajes.getChildren().addAll(lb, metimagen.circuloimage(foto));
							mensajes.setPrefWidth(555);
							mensajes.setAlignment(Pos.TOP_RIGHT);
							mensajes.setPadding(new Insets(10, 10, 10, 10));
							contenido.getChildren().add(mensajes);
							panelder.vvalueProperty().bind(new ObservableValue<Number>() {

								@Override
								public void addListener(InvalidationListener arg0) {
									panelder.vvalueProperty().bind(contenido.heightProperty());
								}

								@Override
								public void removeListener(InvalidationListener arg0) {
		
								}

								@Override
								public void addListener(ChangeListener<? super Number> arg0) {
									
								}

								@Override
								public Number getValue() {
									return 2;
								}

								@Override
								public void removeListener(ChangeListener<? super Number> arg0) {

								}
								
							});
							
							chat.clear();
							
					        }
						
					}
				});
				/// boton enviar
				
				enviar.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						Image foto = new Image("file:anime.jpg");
						
						HBox mensajes = new HBox(20);
						
						Label lb = new Label();
						lb.setText(chat.getText());
						lb.setTextAlignment(TextAlignment.LEFT);
						lb.setWrapText(true);
						lb.setMaxWidth(200);
						lb.setStyle("-fx-background-color: rgb(179,231,244); -fx-background-radius: 8px;");
						lb.setPadding(new Insets(6));
						mensajes.getChildren().addAll(lb, metimagen.circuloimage(foto));
						mensajes.setPrefWidth(555);
						mensajes.setAlignment(Pos.TOP_RIGHT);
						mensajes.setPadding(new Insets(10, 10, 10, 10));
						contenido.getChildren().add(mensajes);
						panelder.vvalueProperty().bind(mensajes.heightProperty());
						chat.clear();
						
						
					}
				}); // aqu� termina la acci�n del boton login
				
				
				
				
				
				HBox chatbox = new HBox(10);
				chatbox.getChildren().addAll(chat, enviar);
				chatbox.setAlignment(Pos.CENTER);

				AnchorPane panel = new AnchorPane();
				panel.getChildren().addAll(chatbox, panelizq, panelder);
				panel.setStyle("-fx-focus-color: transparent;");
				AnchorPane.setBottomAnchor(chatbox, 5d);
				AnchorPane.setRightAnchor(chatbox, 5d);
				AnchorPane.setLeftAnchor(panelizq, 0d);
				AnchorPane.setTopAnchor(panelizq, 50d);
				AnchorPane.setBottomAnchor(panelizq, 0d);
				AnchorPane.setRightAnchor(panelder, 5d);
				AnchorPane.setTopAnchor(panelder, 50d);
				AnchorPane.setBottomAnchor(panelder, 45d);
				
				Stage escenario2 = new Stage();
				Scene escena2 = new Scene(panel, 900, 600);
				//escenario2.getIcons().add(new Image(""));
				//escenario2.setTitle("");
				escenario2.setResizable(false);
				escenario2.setScene(escena2);
				escenario2.show();

			}
		});
		
		/*Image imagen = new Image("file: nombre del archivo.(png o jpg)");
		ImageView img = new ImageView(imagen);
		img.setFitWidth(120);
		img.setFitHeight(120);
		img.setPreserveRatio(true);
		img.setSmooth(true);*/
		
		VBox izq = new VBox(15, user, clave, login);
		
		VBox der = new VBox(15, titulo/*, img*/);
		der.setAlignment(Pos.CENTER);		
		
		AnchorPane raiz = new AnchorPane();
		raiz.getChildren().addAll(izq, der);
		AnchorPane.setLeftAnchor(izq, 20d);
		AnchorPane.setTopAnchor(izq, 45d);
		AnchorPane.setRightAnchor(der, 50d);
		AnchorPane.setTopAnchor(der, 20d);
		
		
		
		Scene escena = new Scene(raiz, 420, 230);
		//escenario.getIcons().add(new Image(""));
		//escenario.setTitle("");
		escenario.setScene(escena);
		escenario.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

/*user.setOnMousePressed(new EventHandler<MouseEvent>() {

	@Override
	public void handle(MouseEvent event) {
		if(user.getText().equals("Usuario")) {
			user.deleteText(0, 7);
		}
	}
});

clave.setOnMousePressed(new EventHandler<MouseEvent>() {

	@Override
	public void handle(MouseEvent event) {
		if(clave.getText().equals("Contraseña")) {
			clave.deleteText(0, 10);
		}					
	}	
});*/

//Imagen circular
/*Image foto = new Image("file:anime.jpg");
Circle user_foto = new Circle(30);
user_foto.setStroke(Color.TRANSPARENT);
user_foto.setFill(new ImagePattern(foto));			        
user_foto.setSmooth(true);*/
