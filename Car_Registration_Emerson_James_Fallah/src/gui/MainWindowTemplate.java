package gui;

import automobily.AbstractVehicle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import kolekce.Seznam;

public class MainWindowTemplate extends BorderPane
{
	// TOP PANE
	private ToolBar toolBar = new ToolBar();
	private Button loadCollectionButton = new Button("Load collection");
	private Button saveCollectionButton = new Button("Save collection");
	private Button loadSampleDataButton = new Button("Load sample data");
	private Button clearButton = new Button("Clear");

	// RIGHT PANE
	private VBox vBox = new VBox();
	private SidebarButton editButton = new SidebarButton("Edit");
	private SidebarButton deleteButton = new SidebarButton("Delete");
	private Separator separator = new Separator();
	private SidebarButton newCarButton = new SidebarButton("New car");
	private SidebarButton newVanButton = new SidebarButton("New van");
	private SidebarButton newTruckButton = new SidebarButton("New truck");

	// CENTER
	private ListView<AbstractVehicle> listView = new ListView<>();

	// SidebarButton
	private class SidebarButton extends Button
	{
		private SidebarButton(String text)
		{
			super(text);
			setPrefWidth(70);
		}
	}

	// CONTROLLER
	private MainWindowController controller;

	// COLLECTION
	private Seznam<AbstractVehicle> collection;

	public MainWindowTemplate(Seznam<AbstractVehicle> collection)
	{
		this.collection = collection;
		this.controller = new MainWindowController(this, collection);

		setPrefWidth(800);
		setPrefHeight(500);
		render();
	}

	public void render()
	{
		setupTop();
		setupRight();
		setupCenter();
	}

	public void setupTop()
	{
		loadCollectionButton.setOnMouseClicked(event -> controller.loadCollectionButtonClicked(event));
		saveCollectionButton.setOnMouseClicked(event -> controller.saveCollectionButtonClicked(event));
		loadSampleDataButton.setOnMouseClicked(event -> controller.loadSampleDataButtonClicked(event));
		clearButton.setOnMouseClicked(event -> controller.clearButtonClicked(event));

		toolBar.getItems().addAll(
				loadCollectionButton,
				saveCollectionButton,
				loadSampleDataButton,
				clearButton
		);

		this.setTop(toolBar);
	}

	public void setupRight()
	{
		vBox.setPrefWidth(100);
		vBox.setFillWidth(true);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10,0,0,0));

		// EDIT
		editButton.setTextFill(Color.valueOf("212529"));
		editButton.setStyle("-fx-background-color: #ffc107;");
		editButton.setOnMouseClicked(event -> controller.editCarButtonClicked(event));

		// DELETE
		deleteButton.setTextFill(Color.WHITE);
		deleteButton.setStyle("-fx-background-color: dc3545;");
		deleteButton.setOnMouseClicked(event -> controller.deleteButtonClicked(event));

		// CREATE BUTTONS
		newCarButton.setOnMouseClicked(event -> controller.newCarButtonClicked(event));
		newVanButton.setOnMouseClicked(event -> controller.newVanButtonClicked(event));
		newTruckButton.setOnMouseClicked(event -> controller.newTruckButtonClicked(event));

		vBox.getChildren().addAll(
				editButton,
				deleteButton,
				separator,
				newCarButton,
				newVanButton,
				newTruckButton
		);

		setRight(vBox);
	}

	public void setupCenter()
	{
		setCenter(listView);
	}

	public ListView<AbstractVehicle> getListView()
	{
		return listView;
	}

	public void refreshListView()
	{
		listView.getItems().clear();
		listView.getItems().addAll(collection.toArray());
		listView.refresh();
	}
}
