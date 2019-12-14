package dad.javafx.Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.model.CV;
import dad.javafx.model.Nacionalidad;
import dad.javafx.model.Personal;
import dad.javafx.root.rootController;
import javafx.beans.binding.ListExpression;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

	@FXML
	private GridPane view;

	@FXML
	private TextField dniField;

	@FXML
	private TextField nombreField;

	@FXML
	private TextField apellidosField;

	@FXML
	private DatePicker fechaPicker;

	@FXML
	private TextArea dirrecionArea;

	@FXML
	private ListView<Nacionalidad> nacionalidadList;

	@FXML
	private TextField codPostalField;

	@FXML
	private TextField localidadField;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private Button addButton;

	@FXML
	private Button removeButton;
	
	private CV cv= rootController.getModel();
	
	 private ListProperty<Nacionalidad> alertList =new SimpleListProperty<Nacionalidad>(FXCollections.observableArrayList());
	 private ListProperty<String> paisList=new SimpleListProperty<String>(FXCollections.observableArrayList());
	 
	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Personal.fxml"));
		loader.setController(this);
		loader.load();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dniField.textProperty().bindBidirectional(cv.getPersonal().identificacioonProperty());
		nombreField.textProperty().bindBidirectional(cv.getPersonal().nombreProperty());
		apellidosField.textProperty().bindBidirectional(cv.getPersonal().apellidosProperty());
		fechaPicker.valueProperty().bindBidirectional(cv.getPersonal().fechanacimientoProperty());
		dirrecionArea.textProperty().bindBidirectional(cv.getPersonal().dirrecionProperty());
		nacionalidadList.itemsProperty().bindBidirectional(cv.getPersonal().nacionalidadProperty());
		cv.getPersonal().nacionalidadSelectedProperty().bind(nacionalidadList.getSelectionModel().selectedItemProperty());
		codPostalField.textProperty().bindBidirectional(cv.getPersonal().codigoPostalProperty());
		localidadField.textProperty().bindBidirectional(cv.getPersonal().localidadProperty());
		paisCombo.valueProperty().bindBidirectional(cv.getPersonal().paisProperty());
		paisCombo.itemsProperty().bind(paisList);
		removeButton.disableProperty().bind(cv.getPersonal().nacionalidadSelectedProperty().isNull());
		cargaPaises();
		paisCombo.setPromptText("Seleciona un País");
		cargaNacionalidad();

	}

	@FXML
	void onAddAction(ActionEvent event) {
		
		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<Nacionalidad>(alertList.get(0), alertList);
		dialog.setTitle("Nueva Nacionalidad");
		dialog.setHeaderText("Añadir Nacionalidad");
		dialog.setContentText("Selecionar una nacionalidad:");

		// Traditional way to get the response value.
		Optional<Nacionalidad> result = dialog.showAndWait();
		if (result.isPresent()){
		   cv.getPersonal().getNacionalidad().add(result.get());
		}
	}

	@FXML
	void onRemoveAction(ActionEvent event) {
		if (cv.getPersonal().getNacionalidad().size()>0) {
		cv.getPersonal().getNacionalidad().remove(cv.getPersonal().getNacionalidadSelected());
		nacionalidadList.getSelectionModel().clearSelection();
		}
	}

	public GridPane getView() {
		return view;
	}

	private  void cargaPaises() {
		final String SEPARATOR="\n";

		      BufferedReader br = null;
		      
		      try {
		        URL url=getClass().getResource("/paises.csv");
		         br =new BufferedReader(new InputStreamReader(url.openStream(),"ISO-8859-1"));
		         String line = br.readLine();
		         while (null!=line) {
		            String [] fields = line.split(SEPARATOR);
		            line = br.readLine();
		            paisList.add(line);
		         }
		         
		      } catch (Exception e) {
		    	  Alert alert = new Alert(AlertType.ERROR);
		    	  alert.setTitle("Error");
		    	  alert.setHeaderText("EL FICHERO NO SE ENCUENTRA");
		    	  alert.showAndWait();
		      } finally {
		         if (null!=br) {
		            try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         }
		      }    
	}
	private void cargaNacionalidad() {
		final String SEPARATOR="\n";
			
		      BufferedReader br = null;
		     
		      ArrayList<Nacionalidad> array=new ArrayList<Nacionalidad>();
		      try {
		        URL url=getClass().getResource("/nacionalidades.csv");
		         br =new BufferedReader(new InputStreamReader(url.openStream(),"ISO-8859-1"));
		         String line = br.readLine();
		         while (null!=line) {
		        	 Nacionalidad nacionalidad=new Nacionalidad();
		            String [] fields = line.split(SEPARATOR);
		            line = br.readLine();
		            nacionalidad.setDenominacion(line);
		            alertList.add(nacionalidad);
		            
		         }
		    		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         if (null!=br) {
		            try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         }
		      } 
	}
	

}
