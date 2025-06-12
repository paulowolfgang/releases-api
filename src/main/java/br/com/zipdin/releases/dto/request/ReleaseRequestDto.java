package br.com.zipdin.releases.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ReleaseRequestDto
{
    @NotBlank
    private String system;

    @NotBlank
    private String version;

    @NotEmpty
    private List<String> commits;

    private String notes;

    @NotBlank
    private String user;
}
