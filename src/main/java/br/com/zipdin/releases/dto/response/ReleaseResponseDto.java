package br.com.zipdin.releases.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ReleaseResponseDto {

    private Long id;
    private String system;
    private String version;
    private List<String> commits;
    private String notes;
    private String user;
    private String userUpdate;
    private LocalDateTime releasedAt;
}
