// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.clinicasmanager.dao;

import ar.com.clinicasmanager.dao.PacienteDAO;
import ar.com.clinicasmanager.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect PacienteDAO_Roo_Jpa_Repository {
    
    declare parents: PacienteDAO extends JpaRepository<Paciente, Long>;
    
    declare parents: PacienteDAO extends JpaSpecificationExecutor<Paciente>;
    
    declare @type: PacienteDAO: @Repository;
    
}
