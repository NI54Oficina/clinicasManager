// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.entity;

import ar.com.clinicasmanager.entity.NodoTratamiento;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.enums.Miembro;
import java.util.Date;

privileged aspect Tratamiento_Roo_JavaBean {
    
    public NodoTratamiento Tratamiento.getTratamiento() {
        return this.tratamiento;
    }
    
    public void Tratamiento.setTratamiento(NodoTratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    public Miembro Tratamiento.getMiembro() {
        return this.miembro;
    }
    
    public void Tratamiento.setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }
    
    public Date Tratamiento.getFechaInicioTratamiento() {
        return this.fechaInicioTratamiento;
    }
    
    public void Tratamiento.setFechaInicioTratamiento(Date fechaInicioTratamiento) {
        this.fechaInicioTratamiento = fechaInicioTratamiento;
    }
    
    public String Tratamiento.getFullName() {
        return this.fullName;
    }
    
    public void Tratamiento.setFullName(String fullName) {
        this.fullName = fullName;
    }
    
}
