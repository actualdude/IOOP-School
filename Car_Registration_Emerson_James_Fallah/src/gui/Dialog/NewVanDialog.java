package gui.Dialog;

import automobily.AbstractVehicle;
import automobily.Van;
import automobily.enums.Color;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import kolekce.KolekceException;
import kolekce.Seznam;
import utils.IsInteger;

import java.util.Optional;

public class NewVanDialog extends Dialog
{
	private Seznam<AbstractVehicle> collection;
	private Van editedVan = null;

	private TextField name = new TextField();
	private ComboBox<Color> colorComboBox = new ComboBox<>();
	private TextField storageSpace = new TextField();
	private ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

	public NewVanDialog(Seznam<AbstractVehicle> collection)
	{
		this.collection = collection;
		this.setTitle("Create new van");
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

		// COLOR
		colorComboBox.setItems(FXCollections.observableArrayList(Color.values()));
		colorComboBox.getSelectionModel().selectFirst();
		grid.add(new Label("Color:"), 0, 1);
		grid.add(colorComboBox, 1, 1);

		// STORAGE SPACE
		grid.add(new Label("Storage space:"), 0, 2);
		grid.add(storageSpace, 1, 2);

		// VALIDATION
		// ----------
		// STORAGE SPACE
		BooleanBinding storageSpaceValid = Bindings.createBooleanBinding(() -> {
			return IsInteger.valueOf(storageSpace.getText());
		}, storageSpace.textProperty());

		// OK BUTTON
		Node okButton = this.getDialogPane().lookupButton(okButtonType);
		okButton.disableProperty().bind(storageSpaceValid.not());

		this.setResultConverter(button -> {
			if (button == okButtonType) {
				if (editedVan != null) {
					return mapFormToEditedVan();
				}

				return new Van(name.getText(), colorComboBox.getValue(), Integer.valueOf(storageSpace.getText()));
			}
			return null;
		});

		this.getDialogPane().setContent(grid);
	}

	public NewVanDialog(Seznam<AbstractVehicle> collection, Van editedVan)
	{
		this(collection);
		this.editedVan = editedVan;
		mapEditedVanToForm(editedVan);
	}

	public void render()
	{
		Optional<Van> optionalDialogValues = this.showAndWait();
		optionalDialogValues.ifPresent(dialogValues -> {
			try {
				if (editedVan == null) {
					collection.pridej(dialogValues);
				}
			}
			catch (KolekceException e) {
				e.printStackTrace();
			}
		});
	}

	private void mapEditedVanToForm(Van editedVan)
	{
		name.setText(editedVan.getName());
		colorComboBox.setValue(editedVan.getColor());
		storageSpace.setText(String.valueOf(editedVan.getStorageSpace()));
	}

	private Van mapFormToEditedVan()
	{
		editedVan.setName(name.getText());
		editedVan.setColor(colorComboBox.getValue());
		editedVan.setStorageSpace(Integer.valueOf(storageSpace.getText()));

		return editedVan;
	}
}
