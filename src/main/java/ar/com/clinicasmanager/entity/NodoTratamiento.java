package ar.com.clinicasmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import com.google.gson.annotations.Expose;

@RooJpaEntity(versionField = "")
@RooJavaBean
public class NodoTratamiento {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Expose
    private Long id;  
    
    @Expose
	private String label;

    @Expose
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value="label ASC")
	private List<NodoTratamiento> children = new ArrayList<NodoTratamiento>(); 
	
	@ManyToOne(fetch = FetchType.LAZY)
	private NodoTratamiento parent;
	
	public NodoTratamiento(NodoTratamiento parent, String label) {
		this.label = label;
		this.parent = parent;
	}
}
