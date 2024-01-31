package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameDTO {

  @NotBlank(message = "Field name is mandatory")
  @Size(max = 255, message = "Maximum length of name is 255 characters")
  private String name;

  private String image;

  @Positive(message = "Field stockTotal must be greater than 0")
  private int stockTotal;

  @Positive(message = "Field privePerDay must be greater than 0")
  private int pricePerDay;

}
