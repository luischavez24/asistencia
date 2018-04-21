package com.guis.asistencia.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author lucho
 */
@Embeddable
public class AsistenciaPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "Sesion_nIdSesion")
    private int sesionnIdSesion;
    @Basic(optional = false)
    @Column(name = "Alumno_vCodigoA")
    private String alumnovCodigoA;

    public AsistenciaPK() {
    }

    public AsistenciaPK(int sesionnIdSesion, String alumnovCodigoA) {
        this.sesionnIdSesion = sesionnIdSesion;
        this.alumnovCodigoA = alumnovCodigoA;
    }

    public int getSesionnIdSesion() {
        return sesionnIdSesion;
    }

    public void setSesionnIdSesion(int sesionnIdSesion) {
        this.sesionnIdSesion = sesionnIdSesion;
    }

    public String getAlumnovCodigoA() {
        return alumnovCodigoA;
    }

    public void setAlumnovCodigoA(String alumnovCodigoA) {
        this.alumnovCodigoA = alumnovCodigoA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) sesionnIdSesion;
        hash += (alumnovCodigoA != null ? alumnovCodigoA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaPK)) {
            return false;
        }
        AsistenciaPK other = (AsistenciaPK) object;
        if (this.sesionnIdSesion != other.sesionnIdSesion) {
            return false;
        }
        if ((this.alumnovCodigoA == null && other.alumnovCodigoA != null) || (this.alumnovCodigoA != null && !this.alumnovCodigoA.equals(other.alumnovCodigoA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AsistenciaPK[ sesionnIdSesion=" + sesionnIdSesion + ", alumnovCodigoA=" + alumnovCodigoA + " ]";
    }
    
}
