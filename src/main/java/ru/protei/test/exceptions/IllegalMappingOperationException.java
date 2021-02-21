package ru.protei.test.exceptions;

public class IllegalMappingOperationException extends Exception {

  private static final long serialVersionUID = 4502144543399649502L;

  public IllegalMappingOperationException() {
    super();
  }

  public IllegalMappingOperationException(final String message) {
    super(message);
  }
}
