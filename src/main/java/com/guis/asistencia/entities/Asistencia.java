package com.guis.asistencia.entities;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author lucho
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedStoredProcedureQuery(
	name="listarAsistenciaSesion",
	procedureName="sp_listar_asistencia_por_sesion",
	parameters = {
		@StoredProcedureParameter(
				mode=ParameterMode.IN,
				name="nro_sesion",
				type=int.class
		)
	},
	resultClasses = Asistencia.class
)
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsistenciaPK asistenciaPK;
    
    @Basic(optional = false)
    @Column(name = "tHora")
    @DateTimeFormat(iso=ISO.TIME)
    private LocalTime tHora;
    @JoinColumn(name = "Sesion_nIdSesion", referencedColumnName = "nIdSesion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sesion sesion;
    @JoinColumn(name = "Alumno_vCodigoA", referencedColumnName = "vCodigoA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;

    public Asistencia() {
    }

    public Asistencia(AsistenciaPK asistenciaPK) {
        this.asistenciaPK = asistenciaPK;
    }

    public Asistencia(int sesionnIdSesion, String alumnovCodigoA) {
        this.asistenciaPK = new AsistenciaPK(sesionnIdSesion, alumnovCodigoA);
    }

    public AsistenciaPK getAsistenciaPK() {
        return asistenciaPK;
    }

    public void setAsistenciaPK(AsistenciaPK asistenciaPK) {
        this.asistenciaPK = asistenciaPK;
    }

    public LocalTime getTHora() {
        return tHora;
    }

    public void setTHora(LocalTime tHora) {
        this.tHora = tHora;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asistenciaPK != null ? asistenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.asistenciaPK == null && other.asistenciaPK != null) || (this.asistenciaPK != null && !this.asistenciaPK.equals(other.asistenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Asistencia[ asistenciaPK=" + asistenciaPK + " ]" + "{Alumno: " + alumno +"}";
    }
    
}
