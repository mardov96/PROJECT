package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	
	@Override
	public void start(Stage escenario) throws Exception {
		
		TextField user = new TextField();
		PasswordField clave = new PasswordField();
		Button login = new Button("Iniciar sesión");
		Label titulo = new Label("");
		
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
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
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
					ImageView foto_user = new ImageView(foto);
					foto_user.setFitWidth(60);
					foto_user.setFitHeight(60);
					foto_user.setPreserveRatio(true);
					foto_user.setSmooth(true);
					foto_user.setStyle("-fx-image-radius: 20px;");
					
					GridPane usuarios = new GridPane();
					Label usuarioId = new Label("Usuario "+(i+1));
					Label mensajeUser = new Label("Hola como estas amigo");
					Label hora = new Label("08:30");
					
					usuarios.add(usuarioId, 0, 0);
					usuarios.add(mensajeUser, 0, 1);
					usuarios.add(hora, 1, 0);

					usuarios.setVgap(10);
					usuarios.setHgap(10);
					
					FlowPane contUser = new FlowPane(foto_user, usuarios);
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
				
				for(int i = 0; i < 10; i++) {
					Image foto = new Image("file:anime.jpg");
					ImageView foto_user = new ImageView(foto);
					foto_user.setFitWidth(60);
					foto_user.setFitHeight(60);
					foto_user.setPreserveRatio(true);
					foto_user.setSmooth(true);
					foto_user.setStyle("-fx-image-radius: 20px;");
					
					HBox mensajes = new HBox(20);
					
					Label lb = new Label("Hola como estas bienvenido al programa "+(i+1));
					lb.setTextAlignment(TextAlignment.JUSTIFY);
					lb.setWrapText(true);
					lb.setMaxWidth(200);
					lb.setStyle("-fx-background-color: rgb(179,231,244); -fx-background-radius: 8px;");
					lb.setPadding(new Insets(6));
					mensajes.getChildren().addAll(foto_user, lb);
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
				
				enviar.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {

						Image foto = new Image("file:anime.jpg");
						ImageView foto_user = new ImageView(foto);
						foto_user.setFitWidth(60);
						foto_user.setFitHeight(60);
						foto_user.setPreserveRatio(true);
						foto_user.setSmooth(true);
						foto_user.setStyle("-fx-image-radius: 20px;");
						
						HBox mensajes = new HBox(20);
						
						Label lb = new Label();
						lb.setText(chat.getText());
						lb.setTextAlignment(TextAlignment.LEFT);
						lb.setWrapText(true);
						lb.setMaxWidth(200);
						lb.setStyle("-fx-background-color: rgb(179,231,244); -fx-background-radius: 8px;");
						lb.setPadding(new Insets(6));
						mensajes.getChildren().addAll(lb, foto_user);
						mensajes.setPrefWidth(555);
						mensajes.setAlignment(Pos.TOP_RIGHT);
						mensajes.setPadding(new Insets(10, 10, 10, 10));
						contenido.getChildren().add(mensajes);
						panelder.vvalueProperty().bind(mensajes.heightProperty());
						chat.clear();
						
						
					}
				});
				
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
