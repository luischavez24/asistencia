package com.guis.asistencia.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucho
 */
@Entity
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vCodigoA", length=10)
    private String vCodigoA;
    @Basic(optional = false)
    @Column(name = "vNombres")
    private String vNombres;
    @Basic(optional = false)
    @Column(name = "vApMaternoA")
    private String vApMaternoA;
    @Basic(optional = false)
    @Column(name = "vApPaternoA")
    private String vApPaternoA;
    @Basic(optional = false)
    @Column(name = "vCorreoA")
    private String vCorreoA;
    @Basic(optional = true)
    @Column(name = "vTelefono")
    private String vTelefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Asistencia> asistenciaList;

    public Alumno() {
    }

    public Alumno(String vCodigoA) {
        this.vCodigoA = vCodigoA;
    }

    public Alumno(String vCodigoA, String vNombres, String vApMaternoA, String vApPaternoA, String vCorreoA, String vTelefono) {
        this.vCodigoA = vCodigoA;
        this.vNombres = vNombres;
        this.vApMaternoA = vApMaternoA;
        this.vApPaternoA = vApPaternoA;
        this.vCorreoA = vCorreoA;
        this.vTelefono = vTelefono;
    }

    public String getVCodigoA() {
        return vCodigoA;
    }

    public void setVCodigoA(String vCodigoA) {
        this.vCodigoA = vCodigoA;
    }

    public String getVNombres() {
        return vNombres;
    }

    public void setVNombres(String vNombres) {
        this.vNombres = vNombres;
    }

    public String getVApMaternoA() {
        return vApMaternoA;
    }

    public void setVApMaternoA(String vApMaternoA) {
        this.vApMaternoA = vApMaternoA;
    }

    public String getVApPaternoA() {
        return vApPaternoA;
    }

    public void setVApPaternoA(String vApPaternoA) {
        this.vApPaternoA = vApPaternoA;
    }

    public String getVCorreoA() {
        return vCorreoA;
    }

    public void setVCorreoA(String vCorreoA) {
        this.vCorreoA = vCorreoA;
    }

    public String getVTelefono() {
        return vTelefono;
    }

    public void setVTelefono(String vTelefono) {
        this.vTelefono = vTelefono;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vCodigoA != null ? vCodigoA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.vCodigoA == null && other.vCodigoA != null) || (this.vCodigoA != null && !this.vCodigoA.equals(other.vCodigoA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return vNombres + " " + vApPaternoA + " " + vApMaternoA;
    }
    
}
