package ar.com.clinicasmanager.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import ar.com.clinicasmanager.entity.enums.ScoreType;
import ar.com.clinicasmanager.ficha.FichaArtritis;
import ar.com.clinicasmanager.ficha.FichaMovilidadDedos;

@RooJavaBean
@RooJpaEntity(versionField = "")
public class Consulta {

	@Embedded
	private EstadoConsulta estado = new EstadoConsulta();	
	
	@ManyToOne
	private Usuario usuarioCreador;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="-M")
	private Date fechaPrimerConsulta = new Date(); 
	
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	private Paciente paciente;

	@OrderBy(value="fechaDiagnostico DESC, id DESC")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	private List<Diagnostico> diagnosticosAnteriores;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Diagnostico diagnostico;
	
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	@OrderBy(value="id DESC")
	private List<Tratamiento> tratamientos;

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	private List<Cirugia> cirugias;

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	private List<Internacion> internaciones;

	@Valid
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Alta alta;

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	@OrderBy(value="id DESC")
	private List<Plan> planes;
	
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	@OrderBy(value="id DESC")
	private List<Evolucion> evoluciones;
	
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	private List<MediaElement> mediaElements;
	
	@Valid
	@Embedded
	private DatosInicialesConsulta datosIniciales; 

	@Valid
	@OrderBy(value="id DESC")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	private List<RespuestasFicha> respuestasFichas; 	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@MapKey(name = "scoreType")
	private Map<ScoreType, Score> scores;
	
	@OrderBy(value="id DESC")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONSULTA_ID")
	private List<FichaMovilidadDedos> fichasMovilidadDedos;
	
	//There is a bug with the view, so it has to be EAGER instead of LAZY
	@Valid
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private FichaArtritis fichaArtritis;
	
	public void addPlan(Plan plan) {
		planes.add(plan);
	}

	public void addEvolucion(Evolucion evolucion) {
		evoluciones.add(evolucion);
	}

	public void addDiagnosticoAnterior(Diagnostico diagnostico) {
		diagnosticosAnteriores.add(diagnostico);
	}

	public void addCirugia(Cirugia cirugia) {
		cirugias.add(cirugia);
	}

	public void addMedia(MediaElement media) {
		mediaElements.add(media);
	}

	public void addTratamiento(Tratamiento tratamiento) {
		tratamientos.add(tratamiento);
	}

	public void addInternacion(Internacion internacion) {
		internaciones.add(internacion);
	}

	public void addRespuestasFicha(RespuestasFicha respuestasFicha) {
		respuestasFichas.add(respuestasFicha);
	}
	
	public void putScore(Score score) {
		scores.put(score.getScoreType(), score);		
	}

	public Score retrieveScore(ScoreType artritis) {		
		return scores.get(artritis);
	}

	public List<FichaMovilidadDedos> getFichasMovilidadDedos() {
		return fichasMovilidadDedos;
	}

	public void setFichasMovilidadDedos(List<FichaMovilidadDedos> fichasMovilidadDedos) {
		this.fichasMovilidadDedos = fichasMovilidadDedos;
	}

	public FichaArtritis getFichaArtritis() {
		return fichaArtritis;
	}

	public void setFichaArtritis(FichaArtritis fichaArtritis) {
		this.fichaArtritis = fichaArtritis;
	}

}
