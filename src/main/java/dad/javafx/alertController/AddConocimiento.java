package dad.javafx.alertController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.model.Idioma;
import dad.javafx.model.Nivel;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddConocimiento implements Initializable {

	@FXML
	private GridPane view;

	@FXML
	private TextField denominacionText;

	@FXML
	private ComboBox<Nivel> nivelCombo;

	@FXML
	private Button limpiaButton;

	private Idioma model = new Idioma();

	public AddConocimiento() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addConocimiento.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Bindings.bindBidirectional(denominacionText.textProperty(), model.denominacionProperty());
		model.nivelProperty().bind(nivelCombo.getSelectionModel().selectedItemProperty());
		nivelCombo.getItems().setAll(Nivel.values());
		limpiaButton.setOnAction((e) -> onLimpiaButton(e));

	}

	private void onLimpiaButton(ActionEvent e) {
		nivelCombo.getSelectionModel().select(null);
	}

	public GridPane getView() {
		return view;
	}

	public Idioma getModel() {
		return model;
	}

}
