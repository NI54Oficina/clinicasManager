// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.entity;

import ar.com.clinicasmanager.entity.Tratamiento;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

privileged aspect Tratamiento_Roo_Jpa_Entity {
    
    declare @type: Tratamiento: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Tratamiento.id;
    
    public Long Tratamiento.getId() {
        return this.id;
    }
    
    public void Tratamiento.setId(Long id) {
        this.id = id;
    }
    
}
