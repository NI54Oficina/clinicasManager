// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.dao;

import ar.com.clinicasmanager.dao.GrupoDAO;
import ar.com.clinicasmanager.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect GrupoDAO_Roo_Jpa_Repository {
    
    declare parents: GrupoDAO extends JpaRepository<Grupo, Long>;
    
    declare parents: GrupoDAO extends JpaSpecificationExecutor<Grupo>;
    
    declare @type: GrupoDAO: @Repository;
    
}
