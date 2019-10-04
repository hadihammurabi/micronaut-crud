package employeeorm.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
// import java.util.Optional;

import employeeorm.model.Employee;

@Singleton
public class EmployeeRepository implements EmployeeRepositoryInf {
  @PersistenceContext
  private EntityManager manager;

  EmployeeRepository(@CurrentSession EntityManager entityManager) {
    this.manager = entityManager;
  }

  @Transactional(readOnly = true)
  public List<Employee> findAll() {
    TypedQuery<Employee> query = manager.createQuery("from Employee", Employee.class);
    return query.getResultList();
  }

  @Transactional(readOnly = true)
  public Employee findById(@NotNull Long id) {
    Employee query = manager.find(Employee.class, id);
    return query;
  }
}
