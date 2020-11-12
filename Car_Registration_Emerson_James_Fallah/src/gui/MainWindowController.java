package gui;

import automobily.AbstractVehicle;
import automobily.Car;
import automobily.Truck;
import automobily.Van;
import automobily.enums.Color;
import automobily.enums.Engine;
import automobily.enums.Fuel;
import gui.Dialog.NewCarDialog;
import gui.Dialog.NewTruckDialog;
import gui.Dialog.NewVanDialog;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import kolekce.KolekceException;
import kolekce.Seznam;

import java.io.*;
import java.util.Arrays;

public class MainWindowController
{
	private final static String BACKUP_PATH = "src/backup.ser";
	private MainWindowTemplate template;
	private Seznam<AbstractVehicle> collection;

	public MainWindowController(MainWindowTemplate template, Seznam<AbstractVehicle> collection)
	{
		this.template = template;
		this.collection = collection;
	}

	public void loadCollectionButtonClicked(MouseEvent event)
	{
		try {
			FileInputStream fileIn = new FileInputStream(BACKUP_PATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			collection.zrus();
			AbstractVehicle items[] = Arrays.stream((Object[]) in.readObject()).toArray(AbstractVehicle[]::new);
			collection.pridej(items);
			template.refreshListView();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Info");
			alert.setHeaderText(null);
			alert.setContentText("The backup of the collection was not yet created.");
			alert.showAndWait();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public void saveCollectionButtonClicked(MouseEvent event)
	{
		try {
			FileOutputStream fileOut = new FileOutputStream(BACKUP_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(collection.toArray());
			out.close();
			fileOut.close();
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Info");
			alert.setHeaderText(null);
			alert.setContentText("Error appeared during saving collection.");
			alert.showAndWait();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public void loadSampleDataButtonClicked(MouseEvent event)
	{
		try {
			collection.zrus();

			collection.pridej(
					new Car("Lamborghini Aventador", Engine.V12, Fuel.PETROL, Color.ORANGE_RED),
					new Car("BMW M6 F13", Engine.V8, Fuel.PETROL, Color.BLACK),
					new Car("Audi S4 B6", Engine.V8, Fuel.PETROL, Color.WHITE),
					new Car("Ferrari LaFerrari", Engine.V12, Fuel.PETROL, Color.RED),
					new Van("Citroen Berlingo", Color.BLUE, 1000),
					new Truck("Freightliner FLA", 2500, 700)
			);

			template.getListView().getItems().clear();
			template.getListView().getItems().addAll(collection.toArray());
		}
		catch (KolekceException e) {
			e.printStackTrace();
		}
	}

	public void clearButtonClicked(MouseEvent event)
	{
		collection.zrus();
		template.refreshListView();
	}

	public void editCarButtonClicked(MouseEvent event)
	{
		AbstractVehicle vehicle = template.getListView().getSelectionModel().getSelectedItem();

		// DECIDE WHICH DIALOG TO OPEN BY INSTANCE TYPE
		if (vehicle == null) {
			showNonSelectedItemAlert();
		}
		else if (vehicle instanceof Car) {
			NewCarDialog newCarDialog = new NewCarDialog(collection, (Car) vehicle);
			newCarDialog.render();
			template.refreshListView();
		}
		else if (vehicle instanceof Van) {
			NewVanDialog newVanDialog = new NewVanDialog(collection, (Van) vehicle);
			newVanDialog.render();
			template.refreshListView();
		}
		else if (vehicle instanceof Truck) {
			NewTruckDialog newTruckDialog = new NewTruckDialog(collection, (Truck) vehicle);
			newTruckDialog.render();
			template.refreshListView();
		}
	}

	public void deleteButtonClicked(MouseEvent event)
	{
		AbstractVehicle vehicle = template.getListView().getSelectionModel().getSelectedItem();

		// DECIDE FACTORY TO OPEN CORRECT DIALOG
		if (vehicle == null) {
			showNonSelectedItemAlert();
		}
		else {
			collection.odeber(vehicle);
			template.refreshListView();
		}

	}

	public void newCarButtonClicked(MouseEvent event)
	{
		NewCarDialog newCarDialog = new NewCarDialog(collection);
		newCarDialog.render();
		template.refreshListView();
	}

	public void newVanButtonClicked(MouseEvent event)
	{
		NewVanDialog newVanDialog = new NewVanDialog(collection);
		newVanDialog.render();
		template.refreshListView();
	}

	public void newTruckButtonClicked(MouseEvent event)
	{
		NewTruckDialog newTruckDialog = new NewTruckDialog(collection);
		newTruckDialog.render();
		template.refreshListView();
	}

	private void showNonSelectedItemAlert()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning!");
		alert.setContentText("None of item is selected.");
		alert.showAndWait();
	}
}
