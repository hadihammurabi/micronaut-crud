package employee.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.http.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import employee.repository.EmployeeRepository;
import employee.model.Employee;
import employee.model.Formatter;

@Controller("/employee")
public class Index {

  @Get(produces=MediaType.APPLICATION_JSON)
  public String index() {
    return (new Gson()).toJson(Formatter.success(EmployeeRepository.getAll()));
  }

  @Get("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String show(@PathVariable int id) {
    Employee data = EmployeeRepository.getById(id);
    return (new Gson()).toJson(Formatter.success(data == null ? new Object() : data));
  }

  @Post(consumes=MediaType.APPLICATION_JSON)
  public String create(@Body String employee) {
    Employee emp = EmployeeRepository.create((new Gson()).fromJson(employee, Employee.class));
    return (new Gson()).toJson(emp != null ? Formatter.success(emp) : Formatter.fail("cannot create employee"));
  }

  @Delete("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String delete(@PathVariable int id) {
    boolean deletion = EmployeeRepository.deleteById(id);
    return (new Gson()).toJson(deletion ? Formatter.success() : Formatter.fail("cannot delete employee with id " + id));
  }

}
