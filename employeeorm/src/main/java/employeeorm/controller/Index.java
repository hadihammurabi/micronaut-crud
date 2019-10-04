package employeeorm.controller;

import java.lang.Math;
import java.util.HashMap;
import java.util.List;

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
  public String index(@QueryValue int page, @QueryValue int limit) {
    List<Employee> employee = repository.findAll(page, limit);
    HashMap<String, Object> data = new HashMap<>();
    data.put("page", Math.ceil(repository.size()/limit));
    data.put("data", employee);
    return (new Gson()).toJson(data);
  }


  @Get("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String index(@PathVariable Long id) {
    return (new Gson()).toJson(repository.findById(id));
  }
}
