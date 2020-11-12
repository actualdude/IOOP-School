package gui.Dialog;

import automobily.AbstractVehicle;
import automobily.Truck;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import kolekce.KolekceException;
import kolekce.Seznam;
import utils.IsInteger;

import java.util.Optional;

public class NewTruckDialog extends Dialog
{
	private Seznam<AbstractVehicle> collection;
	private Truck editedTruck = null;

	private TextField name = new TextField();
	private TextField weight = new TextField();
	private TextField height = new TextField();
	private ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

	public NewTruckDialog(Seznam<AbstractVehicle> collection)
	{
		this.collection = collection;
		this.setTitle("Create new truck");
		this.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

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

		// WEIGHT
		grid.add(new Label("Weight:"), 0, 1);
		grid.add(weight, 1, 1);

		// HEIGHT
		grid.add(new Label("Height:"), 0, 2);
		grid.add(height, 1, 2);

		// VALIDATION
		// ----------
		BooleanBinding weightValid = Bindings.createBooleanBinding(() -> {
			return IsInteger.valueOf(weight.getText());
		}, weight.textProperty());

		BooleanBinding heightValid = Bindings.createBooleanBinding(() -> {
			return IsInteger.valueOf(height.getText());
		}, height.textProperty());

		// OK BUTTON
		Node okButton = this.getDialogPane().lookupButton(okButtonType);
		okButton.disableProperty().bind(weightValid.not().or(heightValid.not()));

		this.setResultConverter(button -> {
			if (button == okButtonType) {
				if (editedTruck != null) {
					return mapFormToEditedTruck();
				}

				return new Truck(name.getText(), Integer.valueOf(weight.getText()), Integer.valueOf(height.getText()));
			}
			return null;
		});

		this.getDialogPane().setContent(grid);
	}

	public NewTruckDialog(Seznam<AbstractVehicle> collection, Truck editedTruck)
	{
		this(collection);
		this.editedTruck = editedTruck;
		mapEditedTruckToForm(editedTruck);
	}

	public void render()
	{
		Optional<Truck> optionalDialogValues = this.showAndWait();
		optionalDialogValues.ifPresent(dialogValues -> {
			try {
				if (editedTruck == null) {
					collection.pridej(dialogValues);
				}
			}
			catch (KolekceException e) {
				e.printStackTrace();
			}
		});
	}

	private void mapEditedTruckToForm(Truck editedTruck)
	{
		name.setText(editedTruck.getName());
		weight.setText(String.valueOf(editedTruck.getWeight()));
		height.setText(String.valueOf(editedTruck.getHeight()));
	}

	private Truck mapFormToEditedTruck()
	{
		editedTruck.setName(name.getText());
		editedTruck.setWeight(Integer.valueOf(weight.getText()));
		editedTruck.setHeight(Integer.valueOf(height.getText()));

		return editedTruck;
	}
}
