package dad.javafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.alertController.AddTelefonoController;
import dad.javafx.model.CV;
import dad.javafx.model.Contacto;
import dad.javafx.model.Email;
import dad.javafx.model.Telefono;
import dad.javafx.model.TipoTelefono;
import dad.javafx.model.Web;
import dad.javafx.root.rootController;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

public class ContactoController implements Initializable {

	@FXML
	private SplitPane view;

	@FXML
	private TableView<Telefono> telefonoView;

	@FXML
	private TableColumn<Telefono, String> telefonoCol;

	@FXML
	private TableColumn<Telefono, TipoTelefono> tipoCol;

	@FXML
	private Button addTelefono;

	@FXML
	private Button removeTelefono;

	@FXML
	private TableView<Email> emailTable;

	@FXML
	private TableColumn<Email, String> emailCol;

	@FXML
	private Button addEmail;

	@FXML
	private Button removeEmail;

	@FXML
	private TableView<Web> webTable;

	@FXML
	private TableColumn<Web, String> urlCol;

	@FXML
	private Button addWeb;

	@FXML
	private Button removeWeb;

	private CV cv= rootController.getModel();

	public ContactoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Contacto.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		telefonoCol.setCellValueFactory(v -> v.getValue().numeroProperty());
		tipoCol.setCellValueFactory(v -> v.getValue().tipoProperty());
		emailCol.setCellValueFactory(v -> v.getValue().direccionProperty());
		urlCol.setCellValueFactory(v -> v.getValue().urlProperty());

		telefonoCol.setCellFactory(TextFieldTableCell.forTableColumn());
		tipoCol.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		urlCol.setCellFactory(TextFieldTableCell.forTableColumn());

		cv.getContacto().telefonoProperty().bindBidirectional(telefonoView.itemsProperty());
		cv.getContacto().emailsProperty().bindBidirectional(emailTable.itemsProperty());
		cv.getContacto().websProperty().bindBidirectional(webTable.itemsProperty());
		
		removeTelefono.disableProperty().bind(telefonoView.getSelectionModel().selectedItemProperty().isNull());
		removeEmail.disableProperty().bind(emailTable.getSelectionModel().selectedItemProperty().isNull());
		removeWeb.disableProperty().bind(webTable.getSelectionModel().selectedItemProperty().isNull());

	}

	@FXML
	public void onAddEmailAction(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo e-mail");
		dialog.setHeaderText("Crear una nueva dirreción de correo");
		dialog.setContentText("E-mail:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Email tempEmail = new Email();
			tempEmail.setDireccion(result.get());
			cv.getContacto().getEmails().addAll(tempEmail);
		}
	}

	@FXML
	public void onAddTelefonoAction(ActionEvent event) {
		try {
			AddTelefonoController newPhone;
			newPhone = new AddTelefonoController();

			Dialog<ButtonType> dialog = new Dialog<ButtonType>();
			dialog.setTitle("Nuevo Telefono");

			ButtonType aplicarButton = new ButtonType("Aplicar", ButtonData.OK_DONE);
			ButtonType cerrarButton = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);

			dialog.getDialogPane().getButtonTypes().addAll(aplicarButton, cerrarButton);

			dialog.getDialogPane().setContent(newPhone.getView());
			Optional<ButtonType> result = dialog.showAndWait();
			if (result.get() == aplicarButton) {
				cv.getContacto().getTelefono().add(newPhone.getModel());
			} else if (result.get() == cerrarButton)
				;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void onAddUrlAction(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog("http://");
		dialog.setTitle("Nueva web");
		dialog.setHeaderText("Crear una nueva dirreción web");
		dialog.setContentText("Url:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Web tempWeb = new Web();
			tempWeb.setUrl(result.get());
			cv.getContacto().getWebs().addAll(tempWeb);
		}
	}

	@FXML
	public void onRemoveEmailAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de borrado");
		alert.setHeaderText("¿Está seguró que de desea borrar?");
		alert.setContentText("E-mail: " + emailTable.getSelectionModel().getSelectedItem().getDireccion());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			emailTable.getItems().remove(emailTable.getSelectionModel().getSelectedItem());
			emailTable.getSelectionModel().clearSelection();
		} else {
			emailTable.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void onRemoveTelefonoAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de borrado");
		alert.setHeaderText("¿Está seguró que de desea borrar?");
		alert.setContentText("Teléfono: " + telefonoView.getSelectionModel().getSelectedItem().getNumero() + ","
				+ telefonoView.getSelectionModel().getSelectedItem().getTipo());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			telefonoView.getItems().remove(telefonoView.getSelectionModel().getSelectedItem());
			telefonoView.getSelectionModel().clearSelection();
		} else {
			telefonoView.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void onUrlWebAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de borrado");
		alert.setHeaderText("¿Está seguró que de desea borrar?");
		alert.setContentText("Url: " + webTable.getSelectionModel().getSelectedItem().getUrl());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			webTable.getItems().remove(webTable.getSelectionModel().getSelectedItem());
			webTable.getSelectionModel().clearSelection();
		} else {
			webTable.getSelectionModel().clearSelection();
		}
	}

	public SplitPane getView() {
		return view;
	}

}
