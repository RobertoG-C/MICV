package dad.javafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.alertController.AddConocimiento;
import dad.javafx.alertController.addIdioma;
import dad.javafx.model.CV;
import dad.javafx.model.Idioma;
import dad.javafx.model.Nivel;
import dad.javafx.root.rootController;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ConocimientoController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private VBox vbox;

	@FXML
	private Button conocimientoButton;

	@FXML
	private Button idiomaButton;

	@FXML
	private Button eliminarButton;

	@FXML
	private TableView<Idioma> table;

	@FXML
	private TableColumn<Idioma, String> denominaCol;

	@FXML
	private TableColumn<Idioma, Nivel> nivelCol;

	private CV cv=rootController.getModel();

	public ConocimientoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nivelCol.setCellValueFactory(v -> v.getValue().nivelProperty());
		denominaCol.setCellValueFactory(v -> v.getValue().denominacionProperty());
		denominaCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelCol.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));


//		eliminarButton.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
		Bindings.bindBidirectional(cv.habilidadesProperty(), table.itemsProperty());

	}

	@FXML
	void AddConocimientoAction(ActionEvent event) {

		AddConocimiento newConocimiento = new AddConocimiento();

		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setTitle("Nuevo conocimiento");

		ButtonType okButton = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		dialog.getDialogPane().setContent(newConocimiento.getView());

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == okButton) {
			cv.getHabilidades().add(newConocimiento.getModel());
		}
	}

	@FXML
	void AddIdiomaAction(ActionEvent event) throws IOException {
		addIdioma idiomaView = new addIdioma();

		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setTitle("Nuevo Idioma");

		ButtonType okButton = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		dialog.getDialogPane().setContent(idiomaView.getView());

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == okButton) {
			cv.getHabilidades().add(idiomaView.getModel());
		}
	}

	@FXML
	void EliminarButton(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar Idioma");
		alert.setHeaderText("¿Estás seguro?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			table.getItems().remove(table.getSelectionModel().getSelectedItem());
			table.getSelectionModel().clearSelection();
		} else
			table.getSelectionModel().clearSelection();
	}


	public BorderPane getView() {
		return view;
	}
	
}
