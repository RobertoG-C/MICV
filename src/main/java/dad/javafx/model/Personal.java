package dad.javafx.model;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Personal {
	
 private StringProperty identificacion=new SimpleStringProperty();
 private StringProperty nombre =new SimpleStringProperty();
 private StringProperty apellidos =new SimpleStringProperty();
 private StringProperty codigoPostal =new SimpleStringProperty();
 private StringProperty dirrecion =new SimpleStringProperty();
 private StringProperty localidad =new SimpleStringProperty();
 private StringProperty pais =new SimpleStringProperty();
 private ObjectProperty<LocalDate> fechanacimiento =new SimpleObjectProperty<LocalDate>();
 private ListProperty<Nacionalidad> nacionalidad=new SimpleListProperty<Nacionalidad>(FXCollections.observableArrayList());
 private ObjectProperty<Nacionalidad> nacionalidadSelected=new SimpleObjectProperty<Nacionalidad>();
 private ListProperty<String> paisList=new SimpleListProperty<String>(FXCollections.observableArrayList());
 
 
 
public final StringProperty identificacioonProperty() {
	return this.identificacion;
}

public final String getIdentificacion() {
	return this.identificacioonProperty().get();
}

public final void setIdentificacion(final String identificacioon) {
	this.identificacioonProperty().set(identificacioon);
}

public final StringProperty nombreProperty() {
	return this.nombre;
}

public final String getNombre() {
	return this.nombreProperty().get();
}

public final void setNombre(final String nombre) {
	this.nombreProperty().set(nombre);
}

public final StringProperty apellidosProperty() {
	return this.apellidos;
}

public final String getApellidos() {
	return this.apellidosProperty().get();
}

public final void setApellidos(final String apellidos) {
	this.apellidosProperty().set(apellidos);
}

public final StringProperty codigoPostalProperty() {
	return this.codigoPostal;
}

public final String getCodigoPostal() {
	return this.codigoPostalProperty().get();
}

public final void setCodigoPostal(final String codigoPostal) {
	this.codigoPostalProperty().set(codigoPostal);
}

public final StringProperty dirrecionProperty() {
	return this.dirrecion;
}

public final String getDirrecion() {
	return this.dirrecionProperty().get();
}

public final void setDirrecion(final String dirrecion) {
	this.dirrecionProperty().set(dirrecion);
}

public final StringProperty localidadProperty() {
	return this.localidad;
}

public final String getLocalidad() {
	return this.localidadProperty().get();
}

public final void setLocalidad(final String localidad) {
	this.localidadProperty().set(localidad);
}

public final StringProperty paisProperty() {
	return this.pais;
}

public final String getPais() {
	return this.paisProperty().get();
}

public final void setPais(final String pais) {
	this.paisProperty().set(pais);
}

public final ObjectProperty<LocalDate> fechanacimientoProperty() {
	return this.fechanacimiento;
}

public final LocalDate getFechanacimiento() {
	return this.fechanacimientoProperty().get();
}

public final void setFechanacimiento(final LocalDate fechanacimiento) {
	this.fechanacimientoProperty().set(fechanacimiento);
}

public final ListProperty<Nacionalidad> nacionalidadProperty() {
	return this.nacionalidad;
}

public final ObservableList<Nacionalidad> getNacionalidad() {
	return this.nacionalidadProperty().get();
}

public final void setNacionalidad(final ObservableList<Nacionalidad> nacionalidad) {
	this.nacionalidadProperty().set(nacionalidad);
}

public final ListProperty<String> paisListProperty() {
	return this.paisList;
}


public final ObservableList<String> getPaisList() {
	return this.paisListProperty().get();
}


public final void setPaisList(final ObservableList<String> paisList) {
	this.paisListProperty().set(paisList);
}

public final ObjectProperty<Nacionalidad> nacionalidadSelectedProperty() {
	return this.nacionalidadSelected;
}


public final Nacionalidad getNacionalidadSelected() {
	return this.nacionalidadSelectedProperty().get();
}


public final void setNacionalidadSelected(final Nacionalidad nacionalidadSelected) {
	this.nacionalidadSelectedProperty().set(nacionalidadSelected);
}







}
