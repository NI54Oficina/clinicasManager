package ar.com.clinicasmanager.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

import ar.com.clinicasmanager.entity.enums.ScoreType;

@RooJavaBean
@RooJpaEntity
public class Score {

	private Double score;
	
	private ScoreType scoreType;
	
	@ElementCollection
	@CollectionTable(name = "RESPUESTAS_SCORE", joinColumns=@JoinColumn(name="SCORE_ID"))
	@OrderColumn
	private List<String> respuestas;
	
	public Score(ScoreType scoreType) {
		this.scoreType = scoreType;
	}

	public Score(ScoreType scoreType, Double score) {
		this.score = score;
		this.scoreType = scoreType;
	}
}
