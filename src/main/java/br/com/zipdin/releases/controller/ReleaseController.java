package br.com.zipdin.releases.controller;

import br.com.zipdin.releases.dto.request.ReleaseRequestDto;
import br.com.zipdin.releases.dto.request.UpdateNotesDto;
import br.com.zipdin.releases.dto.response.ReleaseResponseDto;
import br.com.zipdin.releases.entity.Release;
import br.com.zipdin.releases.security.JwtUtil;
import br.com.zipdin.releases.service.impl.ReleaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/releases")
@RequiredArgsConstructor
public class ReleaseController
{
    private final ReleaseService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ReleaseRequestDto dto,
                                    @RequestHeader("Authorization") String token)
    {
        String userUpdate = JwtUtil.extractUser(token); // mockado
        Release release = service.createRelease(dto, userUpdate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", release.getId(), "message", "Release criado com sucesso."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        Release release = service.getById(id);
        ReleaseResponseDto dto = service.toDto(release);
        return ResponseEntity.ok(Map.of(
                "message", "Release listado com sucesso.",
                "release", dto
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotes(@PathVariable Long id,
                                         @RequestBody @Valid UpdateNotesDto dto)
    {
        service.updateNotes(id, dto.getNotes());
        return ResponseEntity.ok(Map.of("message", "Release atualizado com sucesso."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        service.softDelete(id);
        return ResponseEntity.ok(Map.of("message", "Release deletado com sucesso."));
    }
}
