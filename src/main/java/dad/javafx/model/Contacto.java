package dad.javafx.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Contacto {
	private ListProperty<Telefono> telefono;
	private ListProperty<Email> emails;
	private ListProperty<Web> webs;

	public final ListProperty<Telefono> telefonoProperty() {
		return this.telefono;
	}

	public Contacto() {
		telefono = new SimpleListProperty<Telefono>(FXCollections.emptyObservableList());
		emails = new SimpleListProperty<Email>(FXCollections.emptyObservableList());
		webs = new SimpleListProperty<Web>(FXCollections.emptyObservableList());
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