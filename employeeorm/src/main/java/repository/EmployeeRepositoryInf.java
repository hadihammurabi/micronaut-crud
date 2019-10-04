package employeeorm.repository;

import java.util.List;
import javax.validation.constraints.NotNull;
import employeeorm.model.Employee;

public interface EmployeeRepositoryInf {
    List<Employee> findAll();
    Employee findById(@NotNull Long id);
    // Materi save(@NotNull String kodeMateri, @NotNull String namaMateri);
    // Employee save(@NotNull Employee employee);
    // Employee update(@NotNull Employee employee);
    // void deleteById(@NotNull Long id);
}
