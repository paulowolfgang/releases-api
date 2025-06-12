package br.com.zipdin.releases.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseResponseDto
{
    private Long id;
    private String system;
    private String version;
    private List<String> commits;
    private String notes;
    private String user;
    private String userUpdate;
    private LocalDateTime releasedAt;
}
