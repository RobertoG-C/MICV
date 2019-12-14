package dad.javafx.model;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Idioma extends Conocimiento {
private StringProperty certificacion=new SimpleStringProperty();
	public Idioma() {
		super();
	}
	public final StringProperty certificacionProperty() {
		return this.certificacion;
	}
	@XmlAttribute
	public final String getCertificacion() {
		return this.certificacionProperty().get();
	}
	
	public final void setCertificacion(final String certificacion) {
		this.certificacionProperty().set(certificacion);
	}
	
	
}
