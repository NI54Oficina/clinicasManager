// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.entity;

import ar.com.clinicasmanager.entity.EstadoConsulta;

privileged aspect EstadoConsulta_Roo_JavaBean {
    
    public Boolean EstadoConsulta.getSinDiagnostico() {
        return this.sinDiagnostico;
    }
    
    public void EstadoConsulta.setSinDiagnostico(Boolean sinDiagnostico) {
        this.sinDiagnostico = sinDiagnostico;
    }
    
    public Boolean EstadoConsulta.getConCirugiaPendiente() {
        return this.conCirugiaPendiente;
    }
    
    public void EstadoConsulta.setConCirugiaPendiente(Boolean conCirugiaPendiente) {
        this.conCirugiaPendiente = conCirugiaPendiente;
    }
    
    public Boolean EstadoConsulta.getConInternacion() {
        return this.conInternacion;
    }
    
    public void EstadoConsulta.setConInternacion(Boolean conInternacion) {
        this.conInternacion = conInternacion;
    }
    
    public Boolean EstadoConsulta.getIncompleta() {
        return this.incompleta;
    }
    
    public void EstadoConsulta.setIncompleta(Boolean incompleta) {
        this.incompleta = incompleta;
    }
    
    public Boolean EstadoConsulta.getDadoDeAlta() {
        return this.dadoDeAlta;
    }
    
    public void EstadoConsulta.setDadoDeAlta(Boolean dadoDeAlta) {
        this.dadoDeAlta = dadoDeAlta;
    }
    
}