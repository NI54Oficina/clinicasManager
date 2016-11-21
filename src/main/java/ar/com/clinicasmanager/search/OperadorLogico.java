package ar.com.clinicasmanager.search;

public enum OperadorLogico {

  AND("And"),
  OR("Or");

  private String value;

  private OperadorLogico(String value) {
    this.value = value;
  }

  public String getName() {
    return value;
  }
}
