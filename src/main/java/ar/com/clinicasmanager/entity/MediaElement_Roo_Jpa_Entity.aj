// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.entity;

import ar.com.clinicasmanager.entity.MediaElement;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

privileged aspect MediaElement_Roo_Jpa_Entity {
    
    declare @type: MediaElement: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long MediaElement.id;
    
    public Long MediaElement.getId() {
        return this.id;
    }
    
    public void MediaElement.setId(Long id) {
        this.id = id;
    }
    
}