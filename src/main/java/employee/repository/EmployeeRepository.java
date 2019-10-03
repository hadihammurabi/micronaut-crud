package employee.repository;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import employee.Database;
import employee.model.Employee;

public class EmployeeRepository {
  public static ArrayList<Employee> getAll() {
    ArrayList<Employee> data = new ArrayList();
    try {
      ResultSet res = Database
        .getConnection()
        .createStatement()
        .executeQuery("SELECT * FROM employee");

      while(res.next()) {
        data.add(new Employee(res.getInt("id"), res.getString("name")));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return data;
  }

  public static Employee getById(int id) {
    Employee data = null;
    try {
      ResultSet res = Database
        .getConnection()
        .createStatement()
        .executeQuery("SELECT * FROM employee WHERE id="+ id);

      res.absolute(1);

      data = new Employee(res.getInt("id"), res.getString("name"));
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return data;
  }

  public static Employee create(Employee emp) {
    try {
      PreparedStatement pstmt = Database
        .getConnection()
        .prepareStatement("INSERT INTO employee VALUES (null, '"+emp.name+"')", Statement.RETURN_GENERATED_KEYS);

      if (pstmt.executeUpdate() > 0) {
        ResultSet res = pstmt.getGeneratedKeys();
        if (res.next()) {
          emp.id = res.getInt(1);
        }
      }

      return emp;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static boolean deleteById(int id) {
    try {
      ResultSet res = Database
        .getConnection()
        .createStatement()
        .executeQuery("DELETE FROM employee WHERE id="+ id);
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
}
