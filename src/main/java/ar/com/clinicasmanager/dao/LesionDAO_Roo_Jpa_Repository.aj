// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.dao;

import ar.com.clinicasmanager.dao.LesionDAO;
import ar.com.clinicasmanager.entity.Lesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect LesionDAO_Roo_Jpa_Repository {
    
    declare parents: LesionDAO extends JpaRepository<Lesion, Long>;
    
    declare parents: LesionDAO extends JpaSpecificationExecutor<Lesion>;
    
    declare @type: LesionDAO: @Repository;
    
}