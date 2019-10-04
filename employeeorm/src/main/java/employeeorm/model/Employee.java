package employeeorm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="employee")
public class Employee {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Nama harus diisi.")
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long i) {
    this.id = i;
  }

  public String getName() {
    return name;
  }

  public void setName(String n) {
    this.name = n;
  }

}
