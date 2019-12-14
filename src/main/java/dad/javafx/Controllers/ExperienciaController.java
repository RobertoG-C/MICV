package dad.javafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.alertController.addExperienciaController;
import dad.javafx.alertController.addFormacionController;
import dad.javafx.model.CV;
import dad.javafx.model.Experiencia;
import dad.javafx.model.Titulo;
import dad.javafx.root.rootController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;

public class ExperienciaController implements Initializable {
	@FXML
	private BorderPane view;

	@FXML
	private VBox vbox;

	@FXML
	private Button addButton;

	@FXML
	private Button eliminarButton;

	@FXML
	private TableView<Experiencia> table;

	@FXML
	private TableColumn<Experiencia, LocalDate> desdeCol;

	@FXML
	private TableColumn<Experiencia, LocalDate> hastaCol;

	@FXML
	private TableColumn<Experiencia, String> denominaCol;

	@FXML
	private TableColumn<Experiencia, String> empleadorCol;
	
	private CV cv=rootController.getModel();

	public ExperienciaController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		desdeCol.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaCol.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominaCol.setCellValueFactory(v -> v.getValue().denominacionProperty());
		empleadorCol.setCellValueFactory(v -> v.getValue().empleadorProperty());

		denominaCol.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		desdeCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		
		cv.experienciasProperty().bindBidirectional(table.itemsProperty());
		eliminarButton.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
	}


    @FXML
    void addExperiencia(ActionEvent event) {
    	try {
			addExperienciaController newExp;
			newExp = new addExperienciaController();

			Dialog<ButtonType> dialog = new Dialog<ButtonType>();
			dialog.setTitle("Nueva");

			ButtonType aplicarButton = new ButtonType("Aplicar", ButtonData.OK_DONE);
			ButtonType cerrarButton = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);

			dialog.getDialogPane().getButtonTypes().addAll(aplicarButton, cerrarButton);

			dialog.getDialogPane().setContent(newExp.getView());
			Optional<ButtonType> result = dialog.showAndWait();
			if (result.get() == aplicarButton) {
				cv.getExperiencias().add(newExp.getModel());
			} else if (result.get() == cerrarButton)
				;

		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
    }

    @FXML
    void removeExperiencia(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de borrado");
		alert.setHeaderText("¿Está seguró que de desea borrar?");
		alert.setContentText("Registro: "+table.getSelectionModel().getSelectedItem().getDenominacion()+" "+
				table.getSelectionModel().getSelectedItem().getEmpleador()+" "
				+table.getSelectionModel().getSelectedItem().getDesde()+" "+
				table.getSelectionModel().getSelectedItem().getHasta());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			table.getItems().remove(table.getSelectionModel().getSelectedItem());
			table.getSelectionModel().clearSelection();
		} else {
			table.getSelectionModel().clearSelection();
		}
    }

	public BorderPane getView() {
		return view;
	}
 
}
