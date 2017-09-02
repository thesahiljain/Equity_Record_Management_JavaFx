import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class YesNoBox {
    static boolean choice = false;
    static Stage window;
    public static boolean display(String message){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("Yes");
        yesButton.setMinWidth(60);
        yesButton.setOnAction(e -> {
            choice = true;
            window.close();
        });
        Button noButton = new Button("No");
        noButton.setMinWidth(60);
        noButton.setOnAction(e -> {
            choice = false;
            window.close();
        });

        HBox hBox = new HBox(30);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(yesButton, noButton);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 10, 20, 10));
        layout.getChildren().addAll(label, hBox);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return choice;
    }
}
