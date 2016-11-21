package ar.com.clinicasmanager.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ar.com.clinicasmanager.entity.Consulta;
import ar.com.clinicasmanager.entity.Paciente;
import ar.com.clinicasmanager.search.OperadorLogico;

public class SpecificationUtils {

  public static Specifications<Paciente> appendSpecification(Specifications<Paciente> specifications,
                                                             Specification<Paciente> specificationToAppend) {
    return specifications == null ? Specifications.where(specificationToAppend) : specifications.and(specificationToAppend);
  }

  public static Specifications<Consulta> appendSpecification(OperadorLogico operadorLogico,
                                                             Specifications<Consulta> specifications,
                                                             Specification<Consulta> predicate) {
    Specifications<Consulta> specificationToAppend = Specifications.where(predicate);
    return specifications == null ? Specifications.where(specificationToAppend) :
      operadorLogico.equals(OperadorLogico.AND) ?
        specifications.and(specificationToAppend) :
        specifications.or(specificationToAppend);
  }

  public static String getLikePattern(final String searchTerm) {
    return "%" + searchTerm.toLowerCase() + "%";
  }
}
