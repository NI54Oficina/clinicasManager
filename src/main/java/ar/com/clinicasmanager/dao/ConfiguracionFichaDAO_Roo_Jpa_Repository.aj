// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.dao;

import ar.com.clinicasmanager.dao.ConfiguracionFichaDAO;
import ar.com.clinicasmanager.entity.ConfiguracionFicha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect ConfiguracionFichaDAO_Roo_Jpa_Repository {
    
    declare parents: ConfiguracionFichaDAO extends JpaRepository<ConfiguracionFicha, Long>;
    
    declare parents: ConfiguracionFichaDAO extends JpaSpecificationExecutor<ConfiguracionFicha>;
    
    declare @type: ConfiguracionFichaDAO: @Repository;
    
}
