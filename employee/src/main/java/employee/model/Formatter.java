package employee.model;

class SuccessResponse {
  String message;
  Object data;
  SuccessResponse(Object d) {
    message = "ok";
    data = d;
  }

  SuccessResponse(boolean d) {
    message = "ok";
    data = null;
  }
}

class FailResponse {
  String message;
  FailResponse(String msg) {
    message = msg;
  }
}

public class Formatter {
  public static SuccessResponse success(Object data) {
    return new SuccessResponse(data);
  }

  public static SuccessResponse success() {
    return new SuccessResponse(true);
  }

  public static FailResponse fail(String msg) {
    return new FailResponse(msg);
  }
}
