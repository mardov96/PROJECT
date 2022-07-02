package application;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Met_images {
	
	public static Circle circuloimage(Image imag) {
		Circle cir2 = new Circle(10,10,20);
        cir2.setStroke(Color.SEAGREEN);
        ImageView imageView = new ImageView(imag);
        cir2.setFill(new ImagePattern(imag));
        cir2.setEffect(new DropShadow(+2d, 0d, +2d, Color.DARKSEAGREEN));
        return cir2;
        
	}
	
	

}
