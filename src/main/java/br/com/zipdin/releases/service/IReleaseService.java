package br.com.zipdin.releases.service;

import br.com.zipdin.releases.dto.request.ReleaseRequestDto;
import br.com.zipdin.releases.dto.request.UpdateNotesDto;
import br.com.zipdin.releases.dto.response.ReleaseResponseDto;

public interface IReleaseService
{
    ReleaseResponseDto create(ReleaseRequestDto release, String user);
    ReleaseResponseDto update(Long id, UpdateNotesDto notesUpdated);
    void delete(Long id);
    ReleaseResponseDto findById(Long id);
}
