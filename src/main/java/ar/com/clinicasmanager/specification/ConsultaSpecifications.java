package ar.com.clinicasmanager.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import ar.com.clinicasmanager.entity.Cirugia;
import ar.com.clinicasmanager.entity.Cirugia_;
import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Consulta_;
import ar.com.clinicasmanager.entity.Diagnostico;
import ar.com.clinicasmanager.entity.Diagnostico_;
import ar.com.clinicasmanager.entity.EstadoConsulta;
import ar.com.clinicasmanager.entity.EstadoConsulta_;
import ar.com.clinicasmanager.entity.Lesion;
import ar.com.clinicasmanager.entity.Lesion_;
import ar.com.clinicasmanager.entity.NodoDiagnostico;
import ar.com.clinicasmanager.entity.NodoTratamiento;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.entity.Paciente_;
import ar.com.clinicasmanager.entity.Tratamiento;
import ar.com.clinicasmanager.entity.Tratamiento_;
import ar.com.clinicasmanager.search.Comparacion;
import ar.com.clinicasmanager.search.PacienteSearch;

import com.google.common.collect.FluentIterable;

public class ConsultaSpecifications {

	public static Specification<Consulta> hasLesion(final List<Long> ids) {
		return new Specification<Consulta>() {
			@Override
			public Predicate toPredicate(Root<Consulta> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				SetJoin<Diagnostico, Lesion> lesionNode = root.join(Consulta_.diagnostico).join(Diagnostico_.lesiones);
				Path<NodoDiagnostico> group = lesionNode.get(Lesion_.patologia);
				return group.in(ids);
			}
		};
	}

	public static Specification<Consulta> hasTratamiento(final List<Long> ids) {
		return new Specification<Consulta>() {
			@Override
			public Predicate toPredicate(Root<Consulta> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				
				//Search in tratamientos
				Subquery<Consulta> subquery = query.subquery(Consulta.class);
				Root<Consulta> projection = subquery.from(Consulta.class);
				Join<Consulta, Tratamiento> sqEmp = projection.join(Consulta_.tratamientos);
				Path<NodoTratamiento> group = sqEmp.get(Tratamiento_.tratamiento);
				subquery.select(projection).where(group.in(ids));
				
				//Search in cirugias
				Subquery<Consulta> subquery2 = query.subquery(Consulta.class);
				Root<Consulta> projection2 = subquery2.from(Consulta.class);
				Join<Consulta, Cirugia> sqEmp2 = projection2.join(Consulta_.cirugias);
				Path<NodoTratamiento> group2 = sqEmp2.get(Cirugia_.tratamiento);
				subquery2.select(projection2).where(group2.in(ids));
				
				return cb.or(cb.in(root).value(subquery), cb.in(root).value(subquery2));
			}
		};
	}
	
	public static Specification<Consulta> isDadoDeAlta() {
		return new Specification<Consulta>() {
			@Override
			public Predicate toPredicate(Root<Consulta> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Consulta,EstadoConsulta> estadoNode = root.join(Consulta_.estado);
				return cb.equal(estadoNode.get(EstadoConsulta_.dadoDeAlta), true);
			}
		};
	}
	
	public static Specification<Consulta> primerConsultaBetweenDates(final Date from, final Date to) {
		return new Specification<Consulta>() {
			@Override
			public Predicate toPredicate(Root<Consulta> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate;
				
				if(from != null && to != null) {
					predicate = cb.between(root.get(Consulta_.fechaPrimerConsulta), from, to);
				}
				else if(from != null && to == null) {
					predicate = cb.greaterThanOrEqualTo(root.get(Consulta_.fechaPrimerConsulta), from);
				}
				else {
					predicate = cb.lessThanOrEqualTo(root.get(Consulta_.fechaPrimerConsulta), to);
				}
				
				return predicate;
			}
		};
	}
	
	public static Specification<Consulta> hasPaciente(final PacienteSearch paciente) {
		return new Specification<Consulta>() {
			@Override
			public Predicate toPredicate(Root<Consulta> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Consulta,Paciente> join = root.join(Consulta_.paciente);
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(paciente.getSexo() != null) {
					predicates.add(cb.equal(join.get(Paciente_.sexo), paciente.getSexo()));
				}
				
				if(paciente.getEdad() != null) {
					Comparacion comparacion = paciente.getComparacionEdad() != null ? paciente.getComparacionEdad() : Comparacion.GE;					
					switch(comparacion) {
						case BETWEEN:
							if(paciente.getEdadMax() != null) {
								predicates.add(cb.between(join.get(Paciente_.edad), paciente.getEdad(), paciente.getEdadMax()));
							}
							else {
								predicates.add(cb.ge(join.get(Paciente_.edad), paciente.getEdad()));
							}
							break;
						case EQ:
							predicates.add(cb.equal(join.get(Paciente_.edad), paciente.getEdad()));
							break;
						case GE:
							predicates.add(cb.ge(join.get(Paciente_.edad), paciente.getEdad()));
							break;
						case GT:
							predicates.add(cb.gt(join.get(Paciente_.edad), paciente.getEdad()));
							break;
						case LE:
							predicates.add(cb.le(join.get(Paciente_.edad), paciente.getEdad()));
							break;
						case LT:
							predicates.add(cb.lt(join.get(Paciente_.edad), paciente.getEdad()));
							break;
						default:
							predicates.add(cb.equal(join.get(Paciente_.edad), paciente.getEdad()));
							break;
					}
				}
				
				if(paciente.getCobertura() != null) {
					predicates.add(cb.equal(join.get(Paciente_.cobertura), paciente.getCobertura()));
				}
				
				if(!StringUtils.isEmpty(paciente.getObraSocial())) {
					String obraSocialPattern = SpecificationUtils.getLikePattern(paciente.getObraSocial());
					predicates.add(cb.like(cb.lower(join.get(Paciente_.obraSocial)), obraSocialPattern));
				}
				
				Predicate[] predicatesArray = FluentIterable.from(predicates).toArray(Predicate.class);

				return cb.and(predicatesArray);
			}
		};
	}
	
}
