// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.entity;

import ar.com.clinicasmanager.entity.Ficha;
import ar.com.clinicasmanager.entity.RespuestaFichaPregunta;
import ar.com.clinicasmanager.entity.RespuestasFicha;
import java.util.Date;
import java.util.List;

privileged aspect RespuestasFicha_Roo_JavaBean {
    
    public Date RespuestasFicha.getDate() {
        return this.date;
    }
    
    public void RespuestasFicha.setDate(Date date) {
        this.date = date;
    }
    
    public Ficha RespuestasFicha.getFicha() {
        return this.ficha;
    }
    
    public void RespuestasFicha.setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
    public List<RespuestaFichaPregunta> RespuestasFicha.getRespuestasPregunta() {
        return this.respuestasPregunta;
    }
    
    public void RespuestasFicha.setRespuestasPregunta(List<RespuestaFichaPregunta> respuestasPregunta) {
        this.respuestasPregunta = respuestasPregunta;
    }
    
}