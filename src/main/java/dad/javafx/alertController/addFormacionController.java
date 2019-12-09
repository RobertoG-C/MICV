package dad.javafx.alertController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


public class addFormacionController implements Initializable {

	    @FXML
	    private GridPane view;

	    @FXML
	    private TextField dennominacionText;

	    @FXML
	    private DatePicker desdePicker;

	    @FXML
	    private DatePicker hastaPicker;

	    @FXML
	    private TextField organizadorText;
	    
	    private Titulo model=new Titulo();
	 
	    
	    public addFormacionController() throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addFormacion.fxml"));
			loader.setController(this);
			loader.load();

		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			model.desdeProperty().bind(desdePicker.valueProperty());
			model.hastaProperty().bind(hastaPicker.valueProperty());
			model.organizadorProperty().bind(organizadorText.textProperty());
			model.denominacionProperty().bind(dennominacionText.textProperty());
		}
	    
		public GridPane getView() {
			return view;
		}
		
		public Titulo getModel() {
			return model;
		}

}
