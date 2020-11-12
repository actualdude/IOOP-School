package gui.Dialog;

import automobily.AbstractVehicle;
import automobily.Car;
import automobily.enums.Color;
import automobily.enums.Engine;
import automobily.enums.Fuel;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import kolekce.KolekceException;
import kolekce.Seznam;

import java.util.Optional;

public class NewCarDialog extends Dialog
{
	private Seznam<AbstractVehicle> collection;
	private Car editedCar = null;

	private TextField name = new TextField();
	private ComboBox<Engine> engineComboBox = new ComboBox<>();
	private ComboBox<Fuel> fuelComboBox = new ComboBox<>();
	private ComboBox<Color> colorComboBox = new ComboBox<>();
	private ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

	public NewCarDialog(Seznam<AbstractVehicle> collection)
	{
		this.collection = collection;
		this.setTitle("Create new car");
		this.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		// GRID
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		// INPUTS
		// ------
		// NAME
		grid.add(new Label("Name:"), 0, 0);
		grid.add(name, 1, 0);

		// ENGINE
		engineComboBox.setItems(FXCollections.observableArrayList(Engine.values()));
		engineComboBox.getSelectionModel().selectFirst();
		grid.add(new Label("Engine:"), 0, 1);
		grid.add(engineComboBox, 1, 1);

		// FUEL
		fuelComboBox.setItems(FXCollections.observableArrayList(Fuel.values()));
		fuelComboBox.getSelectionModel().selectFirst();
		grid.add(new Label("Fuel:"), 0, 2);
		grid.add(fuelComboBox, 1, 2);

		// COLOR
		colorComboBox.setItems(FXCollections.observableArrayList(Color.values()));
		colorComboBox.getSelectionModel().selectFirst();
		grid.add(new Label("Color:"), 0, 3);
		grid.add(colorComboBox, 1, 3);

		this.setResultConverter(button -> {
			if (button == okButton) {
				if (editedCar != null) {
					return mapFormToEditedCar();
				}

				return new Car(name.getText(), engineComboBox.getValue(), fuelComboBox.getValue(), colorComboBox.getValue());
			}
			return null;
		});

		this.getDialogPane().setContent(grid);
	}

	public NewCarDialog(Seznam<AbstractVehicle> collection, Car editedCar)
	{
		this(collection);
		this.editedCar = editedCar;
		mapEditedCarToForm(editedCar);
	}

	public void render()
	{
		Optional<Car> optionalDialogValues = this.showAndWait();
		optionalDialogValues.ifPresent(dialogValues -> {
			try {
				if (editedCar == null) {
					collection.pridej(dialogValues);
				}
			}
			catch (KolekceException e) {
				e.printStackTrace();
			}
		});
	}

	private void mapEditedCarToForm(Car editedCar)
	{
		name.setText(editedCar.getName());
		engineComboBox.setValue(editedCar.getEngine());
		fuelComboBox.setValue(editedCar.getFuel());
		colorComboBox.setValue(editedCar.getColor());
	}

	private Car mapFormToEditedCar()
	{
		editedCar.setName(name.getText());
		editedCar.setEngine(engineComboBox.getValue());
		editedCar.setFuel(fuelComboBox.getValue());
		editedCar.setColor(colorComboBox.getValue());

		return editedCar;
	}
}
