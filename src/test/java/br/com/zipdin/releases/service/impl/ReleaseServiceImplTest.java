package br.com.zipdin.releases.service.impl;

import br.com.zipdin.releases.dto.request.ReleaseRequestDto;
import br.com.zipdin.releases.dto.request.UpdateNotesDto;
import br.com.zipdin.releases.dto.response.ReleaseResponseDto;
import br.com.zipdin.releases.entity.Release;
import br.com.zipdin.releases.exception.ReleaseNotFoundException;
import br.com.zipdin.releases.repository.IReleaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReleaseServiceImplTest
{
    private IReleaseRepository releaseRepository;
    private ReleaseServiceImpl releaseService;

    @BeforeEach
    void setUp()
    {
        releaseRepository = mock(IReleaseRepository.class);
        releaseService = new ReleaseServiceImpl(releaseRepository);
    }

    @Test
    void shouldCreateReleaseSuccessfully()
    {
        ReleaseRequestDto request = new ReleaseRequestDto();

        request.setSystem("SystemX");
        request.setVersion("1.0.0");
        request.setCommits(List.of("commit 1", "commit 2"));
        request.setUser("user");

        Release saved = Release.builder()
                .id(1L)
                .system("SystemX")
                .version("1.0.0")
                .commits(request.getCommits())
                .user("user")
                .userUpdate("mockedUser")
                .releasedAt(LocalDateTime.now())
                .build();

        when(releaseRepository.save(any())).thenReturn(saved);

        ReleaseResponseDto response = releaseService.create(request, "mockedUser");

        assertNotNull(response);
        assertEquals("SystemX", response.getSystem());
        assertEquals("mockedUser", response.getUserUpdate());
    }

    @Test
    void shouldUpdateNotesSuccessfully()
    {
        Release existing = Release.builder()
                .id(2L)
                .notes("Old note")
                .build();

        when(releaseRepository.findByIdAndDeletedAtIsNull(2L)).thenReturn(Optional.of(existing));
        when(releaseRepository.save(any())).thenReturn(existing);

        UpdateNotesDto request = new UpdateNotesDto();
        request.setNotes("New note");

        ReleaseResponseDto response = releaseService.update(2L, request);

        assertEquals("New note", response.getNotes());
    }

    @Test
    void shouldDeleteReleaseSoftly()
    {
        Release existing = Release.builder()
                .id(3L)
                .build();

        when(releaseRepository.findById(3L)).thenReturn(Optional.of(existing));

        releaseService.delete(3L);

        assertNotNull(existing.getDeletedAt());
        verify(releaseRepository).save(existing);
    }

    @Test
    void shouldFindReleaseById()
    {
        Release release = Release.builder()
                .id(4L)
                .system("SystemY")
                .build();

        when(releaseRepository.findByIdAndDeletedAtIsNull(4L)).thenReturn(Optional.of(release));

        ReleaseResponseDto response = releaseService.findById(4L);

        assertEquals(4L, response.getId());
        assertEquals("SystemY", response.getSystem());
    }

    @Test
    void shouldThrowExceptionWhenReleaseNotFound()
    {
        when(releaseRepository.findByIdAndDeletedAtIsNull(99L)).thenReturn(Optional.empty());

        assertThrows(ReleaseNotFoundException.class, () -> releaseService.findById(99L));
    }
}
