package ar.com.clinicasmanager.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.Paciente_;
import ar.com.clinicasmanager.entity.enums.Cobertura;
import ar.com.clinicasmanager.entity.enums.Ocupacion;
import ar.com.clinicasmanager.entity.enums.Sexo;

public class PacienteSpecifications {

	public static Specification<Paciente> nombreIsLike(final String nombre) {
		return new Specification<Paciente>() {
			@Override
			public Predicate toPredicate(Root<Paciente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String nombrePattern = SpecificationUtils.getLikePattern(nombre);
				return cb.like(cb.lower(root.<String>get(Paciente_.nombre)), nombrePattern);
			}

		};
	}
	
	public static Specification<Paciente> ocupacionEquals(final Ocupacion ocupacion) {
		return new Specification<Paciente>() {
			@Override
			public Predicate toPredicate(Root<Paciente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Ocupacion>get(Paciente_.ocupacion), ocupacion);
			}
		};
	}
	
	public static Specification<Paciente> sexoEquals(final Sexo sexo) {
		return new Specification<Paciente>() {
			@Override
			public Predicate toPredicate(Root<Paciente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Sexo>get(Paciente_.sexo), sexo);
			}
		};
	}
	
	public static Specification<Paciente> coberturaEquals(final Cobertura cobertura) {
		return new Specification<Paciente>() {
			@Override
			public Predicate toPredicate(Root<Paciente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Cobertura>get(Paciente_.cobertura), cobertura);
			}
		};
	}	
}
