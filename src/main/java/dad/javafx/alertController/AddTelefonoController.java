package dad.javafx.alertController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.model.Telefono;
import dad.javafx.model.TipoTelefono;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddTelefonoController implements Initializable {
    @FXML
    private GridPane view;

    @FXML
    private ComboBox<TipoTelefono> tipoCombo;

    @FXML
    private TextField numero;
    
   private Telefono model=new Telefono();
   
    public AddTelefonoController() throws IOException {
		FXMLLoader loader =new FXMLLoader(getClass().getResource("/fxml/addTelefonoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.numeroProperty().bind(numero.textProperty());
		model.tipoProperty().bind(tipoCombo.getSelectionModel().selectedItemProperty());
		tipoCombo.getItems().setAll(TipoTelefono.values());
		tipoCombo.getSelectionModel().selectFirst();
	}
    
	public Telefono getModel() {
		return model;
	}
    
	public GridPane getView() {
		return view;
	}
}
