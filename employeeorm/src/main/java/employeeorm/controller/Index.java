package employeeorm.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.http.MediaType;

import com.google.gson.*;
import employeeorm.repository.EmployeeRepository;
import employeeorm.repository.EmployeeRepositoryInf;
import employeeorm.model.Employee;

@Controller("/employee")
public class Index {
  private EmployeeRepositoryInf repository;

  Index(EmployeeRepositoryInf r) {
    this.repository = r;
  }

  @Get(produces=MediaType.APPLICATION_JSON)
  public String index() {
    return (new Gson()).toJson(repository.findAll());
  }


  @Get("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String index(@PathVariable Long id) {
    return (new Gson()).toJson(repository.findById(id));
  }
}
