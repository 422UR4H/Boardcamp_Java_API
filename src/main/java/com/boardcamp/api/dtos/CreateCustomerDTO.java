package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO {

  @NotBlank(message = "Field name is mandatory")
  @Size(max = 255, message = "Maximum length of name is 255 characters")
  private String name;

  @NotBlank(message = "Field cpf is mandatory")
  @Size(min = 11, max = 11, message = "The length of cpf must be 11 characters")
  private String cpf;
}
