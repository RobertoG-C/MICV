package dad.javafx.alertController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.model.Experiencia;
import dad.javafx.model.Titulo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class addExperienciaController implements Initializable {

	    @FXML
	    private GridPane view;

	    @FXML
	    private TextField denominacionText;

	    @FXML
	    private DatePicker desdePicker;

	    @FXML
	    private DatePicker hastaPicker;

	    @FXML
	    private TextField empleadorText;
	    
	    private Experiencia model=new Experiencia();
	 
	    
	    public addExperienciaController() throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addExperiencia.fxml"));
			loader.setController(this);
			loader.load();

		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			model.desdeProperty().bind(desdePicker.valueProperty());
			model.hastaProperty().bind(hastaPicker.valueProperty());
			model.empleadorProperty().bind(empleadorText.textProperty());
			model.denominacionProperty().bind(denominacionText.textProperty());
		}
	    
		public GridPane getView() {
			return view;
		}
		
		public Experiencia getModel() {
			return model;
		}

}
