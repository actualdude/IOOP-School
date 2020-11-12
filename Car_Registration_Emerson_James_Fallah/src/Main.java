import automobily.AbstractVehicle;
import gui.MainWindowTemplate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kolekce.Seznam;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Seznam<AbstractVehicle> collection = new Seznam<>(100);
		MainWindowTemplate mainWindowTemplate = new MainWindowTemplate(collection);
		Scene scene = new Scene(mainWindowTemplate);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Car registration - Emerson James Fallah");
		primaryStage.show();
	}

	public static void main(String[] args) { launch(args); }
}
