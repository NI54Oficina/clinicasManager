// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.dao;

import ar.com.clinicasmanager.dao.FichaDAO;
import ar.com.clinicasmanager.entity.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect FichaDAO_Roo_Jpa_Repository {
    
    declare parents: FichaDAO extends JpaRepository<Ficha, Long>;
    
    declare parents: FichaDAO extends JpaSpecificationExecutor<Ficha>;
    
    declare @type: FichaDAO: @Repository;
    
}
