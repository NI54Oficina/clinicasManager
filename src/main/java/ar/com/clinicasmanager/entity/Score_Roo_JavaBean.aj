// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.entity;

import ar.com.clinicasmanager.entity.Score;
import ar.com.clinicasmanager.entity.enums.ScoreType;
import java.util.List;

privileged aspect Score_Roo_JavaBean {
    
    public Double Score.getScore() {
        return this.score;
    }
    
    public void Score.setScore(Double score) {
        this.score = score;
    }
    
    public ScoreType Score.getScoreType() {
        return this.scoreType;
    }
    
    public void Score.setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }
    
    public List<String> Score.getRespuestas() {
        return this.respuestas;
    }
    
    public void Score.setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }
    
}
