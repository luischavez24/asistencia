package com.guis.asistencia.entities;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author lucho
 */
@Entity
@Table(name = "sesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sesion.findAll", query = "SELECT s FROM Sesion s")})
public class Sesion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "nIdSesion")
    private Integer nIdSesion;
    
    @Basic(optional = false)
    @DateTimeFormat(iso=ISO.DATE)
    @Column(name = "dFecha")
    private LocalDate dFecha;
    
    @DateTimeFormat(iso=ISO.TIME)
    @Basic(optional = false)
    @Column(name = "tHoraInicio")
    private LocalTime tHoraInicio;
    
    @DateTimeFormat(iso=ISO.TIME)
    @Basic(optional = false)
    @Column(name = "tHoraFin")
    private LocalTime tHoraFin;
    
    @DateTimeFormat(iso=ISO.TIME)
    @Basic(optional = false)
    @Column(name = "tTolerancia")
    private LocalTime tTolerancia;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sesion")
    private List<Asistencia> asistenciaList;

    public Sesion() {
    	tHoraFin = LocalTime.now();
    }

    public Sesion(Integer nIdSesion) {
        this.nIdSesion = nIdSesion;
    }


    public Integer getNIdSesion() {
        return nIdSesion;
    }

    public void setNIdSesion(Integer nIdSesion) {
        this.nIdSesion = nIdSesion;
    }

    public LocalDate getDFecha() {
        return dFecha;
    }

    public void setDFecha(LocalDate dFecha) {
        this.dFecha = dFecha;
    }

    public LocalTime getTHoraInicio() {
        return tHoraInicio;
    }

    public void setTHoraInicio(LocalTime tHoraInicio) {
        this.tHoraInicio = tHoraInicio;
    }

    public LocalTime getTHoraFin() {
        return tHoraFin;
    }

    public void setTHoraFin(LocalTime tHoraFin) {
        this.tHoraFin = tHoraFin;
    }

    public LocalTime getTTolerancia() {
        return tTolerancia;
    }

    public void setTTolerancia(LocalTime tTolerancia) {
        this.tTolerancia = tTolerancia;
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
        hash += (nIdSesion != null ? nIdSesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sesion)) {
            return false;
        }
        Sesion other = (Sesion) object;
        if ((this.nIdSesion == null && other.nIdSesion != null) || (this.nIdSesion != null && !this.nIdSesion.equals(other.nIdSesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sesion[ nIdSesion=" + nIdSesion + " ]";
    }
    
}
