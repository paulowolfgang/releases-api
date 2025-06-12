package br.com.zipdin.releases.controller;

import br.com.zipdin.releases.dto.request.ReleaseRequestDto;
import br.com.zipdin.releases.dto.request.UpdateNotesDto;
import br.com.zipdin.releases.dto.response.ReleaseResponseDto;
import br.com.zipdin.releases.security.JwtUtil;
import br.com.zipdin.releases.service.IReleaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/releases")
public class ReleaseController
{
    private final IReleaseService releaseService;

    public ReleaseController(IReleaseService releaseService)
    {
        this.releaseService = releaseService;
    }

    @PostMapping
    public ResponseEntity<ReleaseResponseDto> create(@RequestBody @Valid ReleaseRequestDto request,
                                    @RequestHeader("Authorization") String token)
    {
        String user = JwtUtil.extractUser(token);
        ReleaseResponseDto response = releaseService.create(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReleaseResponseDto> updateNotes(@PathVariable Long id,
                                         @RequestBody @Valid UpdateNotesDto request)
    {
        return ResponseEntity.status(HttpStatus.OK).body(releaseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        releaseService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseResponseDto> getById(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(releaseService.findById(id));
    }
}
