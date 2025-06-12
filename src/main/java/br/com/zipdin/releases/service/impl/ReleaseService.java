package br.com.zipdin.releases.service.impl;

import br.com.zipdin.releases.dto.request.ReleaseRequestDto;
import br.com.zipdin.releases.dto.response.ReleaseResponseDto;
import br.com.zipdin.releases.entity.Release;
import br.com.zipdin.releases.repository.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReleaseService
{
    private final ReleaseRepository repository;

    public Release createRelease(ReleaseRequestDto dto, String userUpdate)
    {
        Release release = Release.builder()
                .system(dto.getSystem().trim())
                .version(dto.getVersion())
                .commits(dto.getCommits())
                .notes(dto.getNotes())
                .user(dto.getUser())
                .userUpdate(userUpdate)
                .build();
        return repository.save(release);
    }

    public Release getById(Long id)
    {
        return repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new RuntimeException("Release não encontrado"));
    }

    public void updateNotes(Long id, String notes)
    {
        Release release = getById(id);
        release.setNotes(notes);
        repository.save(release);
    }

    public void softDelete(Long id)
    {
        Release release = getById(id);
        release.setDeletedAt(LocalDateTime.now());
        repository.save(release);
    }

    public ReleaseResponseDto toDto(Release release)
    {
        return ReleaseResponseDto.builder()
                .id(release.getId())
                .system(release.getSystem())
                .version(release.getVersion())
                .commits(release.getCommits())
                .notes(release.getNotes())
                .user(release.getUser())
                .userUpdate(release.getUserUpdate())
                .releasedAt(release.getReleasedAt())
                .build();
    }
}
