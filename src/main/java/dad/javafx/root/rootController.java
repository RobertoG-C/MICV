package dad.javafx.root;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.Controllers.ConocimientoController;
import dad.javafx.Controllers.ContactoController;
import dad.javafx.Controllers.ExperienciaController;
import dad.javafx.Controllers.FormacionController;
import dad.javafx.Controllers.PersonalController;
import dad.javafx.model.CV;
import dad.javafx.model.Personal;
import dad.javafx.persistenciaxml.JAXBUtil;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class rootController implements Initializable {

	@FXML
	private VBox view;

	@FXML
	private Menu fileMenuItem;

	@FXML
	private MenuItem newItem;

	@FXML
	private MenuItem openItem;

	@FXML
	private MenuItem saveItem;

	@FXML
	private MenuItem saveAsItem;

	@FXML
	private MenuItem exitItem;

	@FXML
	private Menu helpItem;

	@FXML
	private MenuItem aboutMyItem;

	@FXML
	private Tab personalTab;

	@FXML
	private Tab contactoTab;

	@FXML
	private Tab formacionTab;

	@FXML
	private Tab experienciaTab;

	@FXML
	private Tab conocimientosTab;

	@FXML
	private AnchorPane personalAncho;

	private PersonalController personalController = new PersonalController();
	private ContactoController contactoController = new ContactoController();
	private FormacionController formacionController = new FormacionController();
	private ExperienciaController experienciaController = new ExperienciaController();
	private ConocimientoController conocimientoController = new ConocimientoController();
	private CV model = new CV();

	public rootController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rootView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contactoTab.setContent(contactoController.getView());
		personalTab.setContent(personalController.getView());
		formacionTab.setContent(formacionController.getView());
		experienciaTab.setContent(experienciaController.getView());
		conocimientosTab.setContent(conocimientoController.getView());

	}

	@FXML
	void onNewAction(ActionEvent event) {
		personalController.getModel().setIdentificacion("");
		personalController.getModel().setNombre("");
		personalController.getModel().setApellidos("");
		personalController.getModel().setCodigoPostal("");
		personalController.getModel().setDirrecion("");
		personalController.getModel().setFechanacimiento(null);
		personalController.getModel().setLocalidad("");
		personalController.getModel().setNacionalidad(null);
		personalController.getModel().setPais("");
		contactoController.getModel().setTelefono(FXCollections.observableArrayList());
		contactoController.getModel().setEmails(FXCollections.observableArrayList());
		contactoController.getModel().setWebs(FXCollections.observableArrayList());
		formacionController.getModel().set(FXCollections.observableArrayList());
		experienciaController.getModel().set(FXCollections.observableArrayList());
		conocimientoController.getModel().set(FXCollections.observableArrayList());
	}

	@FXML
	void onOpenAction(ActionEvent event) {

	}

	@FXML
	void onSalirAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Cerrar App");
		alert.setHeaderText("Está apunto de cerrar la app");
		alert.setContentText("¿Seguró que desea salir?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
		}
	}

	@FXML
	void onSaveASAction(ActionEvent event) throws Exception {
		
	}

	@FXML
	void onSaveAction(ActionEvent event) throws Exception {
	/*	String nombreFichero = "";

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Dame un nombre");
		dialog.setHeaderText("Se requiere un nombre para guardar el .cv");
		dialog.setContentText("Nombre: ");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			nombreFichero = result.get();
		}

		JAXBUtil.save(model, new File(nombreFichero + ".cv"));*/

	}

	public VBox getView() {
		return view;
	}

}
