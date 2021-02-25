package ru.protei.test.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.protei.test.dtos.UserDto;
import ru.protei.test.dtos.UserIdDto;
import ru.protei.test.dtos.UserStatusDto;
import ru.protei.test.exceptions.IllegalMappingOperationException;
import ru.protei.test.exceptions.ResourceAlreadyExistsException;
import ru.protei.test.exceptions.ResourceNotFoundException;
import ru.protei.test.services.UserService;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

  private final UserService userService;

  @Operation(summary = "Save user with provided data")
  @PostMapping(
      path = "/users",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  public UserIdDto saveUser(final @RequestBody @Valid UserDto userDto)
      throws ResourceAlreadyExistsException, IllegalMappingOperationException {
    return userService.saveUser(userDto);
  }

  @Operation(summary = "Get user with specified id")
  @GetMapping(path = "/users/{id}", produces = APPLICATION_JSON_VALUE)
  public UserDto findUserById(final @PathVariable Long id)
      throws ResourceNotFoundException, IllegalMappingOperationException {
    return userService.findUserById(id);
  }

  @Operation(summary = "Change status of the user with specified id")
  @PatchMapping(
      path = "/users/{id}",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public UserStatusDto changeUserStatus(
      final @PathVariable Long id, final @RequestBody @Valid UserStatusDto userStatusDto)
      throws ResourceNotFoundException {
    return userService.changeUserStatus(id, userStatusDto);
  }
}
