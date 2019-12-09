package dad.javafx.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacto {
private ListProperty<Telefono> telefono=new SimpleListProperty<Telefono>(FXCollections.emptyObservableList());
private ListProperty<Email>emails=new SimpleListProperty<Email>(FXCollections.emptyObservableList());
private ListProperty<Web>webs=new SimpleListProperty<Web>(FXCollections.emptyObservableList());
public final ListProperty<Telefono> telefonoProperty() {
	return this.telefono;
}

public final ObservableList<Telefono> getTelefono() {
	return this.telefonoProperty().get();
}

public final void setTelefono(final ObservableList<Telefono> telefono) {
	this.telefonoProperty().set(telefono);
}

public final ListProperty<Email> emailsProperty() {
	return this.emails;
}

public final ObservableList<Email> getEmails() {
	return this.emailsProperty().get();
}

public final void setEmails(final ObservableList<Email> emails) {
	this.emailsProperty().set(emails);
}

public final ListProperty<Web> websProperty() {
	return this.webs;
}

public final ObservableList<Web> getWebs() {
	return this.websProperty().get();
}

public final void setWebs(final ObservableList<Web> webs) {
	this.websProperty().set(webs);
}


}