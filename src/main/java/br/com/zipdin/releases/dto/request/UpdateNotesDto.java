package br.com.zipdin.releases.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateNotesDto
{
    @NotBlank
    private String notes;
}
