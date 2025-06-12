package br.com.zipdin.releases.service.impl;

import br.com.zipdin.releases.dto.mapper.ReleaseMapper;
import br.com.zipdin.releases.dto.request.ReleaseRequestDto;
import br.com.zipdin.releases.dto.request.UpdateNotesDto;
import br.com.zipdin.releases.dto.response.ReleaseResponseDto;
import br.com.zipdin.releases.entity.Release;
import br.com.zipdin.releases.exception.ReleaseNotFoundException;
import br.com.zipdin.releases.repository.IReleaseRepository;
import br.com.zipdin.releases.service.IReleaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReleaseServiceImpl implements IReleaseService
{
    private final IReleaseRepository releaseRepository;

    public ReleaseServiceImpl(IReleaseRepository releaseRepository)
    {
        this.releaseRepository = releaseRepository;
    }

    @Override
    @Transactional
    public ReleaseResponseDto create(ReleaseRequestDto request, String user)
    {
        Release release = ReleaseMapper.converter(request);
        release.setUserUpdate(user);
        release = releaseRepository.save(release);

        return ReleaseMapper.converter(release);
    }

    @Override
    @Transactional
    public ReleaseResponseDto update(Long id, UpdateNotesDto notesUpdated)
    {
        Release release = releaseRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ReleaseNotFoundException(String.format("Release não encontrada para o ID: " + id)));

        release.setNotes(notesUpdated.getNotes());
        release = releaseRepository.save(release);

        return ReleaseMapper.converter(release);
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        Release release = releaseRepository.findById(id)
                .orElseThrow(() -> new ReleaseNotFoundException(String.format("Release não encontrada para o ID: " + id)));

        release.setDeletedAt(LocalDateTime.now());
        releaseRepository.save(release);
    }

    @Override
    @Transactional(readOnly = true)
    public ReleaseResponseDto findById(Long id)
    {
        Release release = releaseRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ReleaseNotFoundException(String.format("Release não encontrada para o ID: " + id)));

        return ReleaseMapper.converter(release);
    }
}
