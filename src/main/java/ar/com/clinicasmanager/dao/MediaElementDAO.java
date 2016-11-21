package ar.com.clinicasmanager.dao;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import ar.com.clinicasmanager.entity.MediaElement;

@RooJpaRepository(domainType = MediaElement.class)
public interface MediaElementDAO {

}
