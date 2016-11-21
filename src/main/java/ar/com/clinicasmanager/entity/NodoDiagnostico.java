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

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import com.google.gson.annotations.Expose;

@RooJpaEntity(versionField = "")
@RooJavaBean
public class NodoDiagnostico {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Expose
    private Long id; 
	
	@Expose
	private String label;

	@Expose
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NodoDiagnostico> children = new ArrayList<NodoDiagnostico>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private NodoDiagnostico parent;
	
	@Expose
	private Boolean displayInSumamry;
	
	public NodoDiagnostico(NodoDiagnostico parent, String label) {
		this.label = label;
		this.parent = parent;
		this.displayInSumamry = false; 
	}	
	
	public NodoDiagnostico(NodoDiagnostico parent, String label, Boolean displayInSummary) {
		this.label = label;
		this.parent = parent;
		this.displayInSumamry = true;
	}

	public Boolean getDisplayInSumamry() {
		return displayInSumamry;
	}

	public void setDisplayInSumamry(Boolean displayInSumamry) {
		this.displayInSumamry = displayInSumamry;
	}

}
