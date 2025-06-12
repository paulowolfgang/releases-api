package br.com.zipdin.releases.repository;

import br.com.zipdin.releases.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReleaseRepository extends JpaRepository<Release, Long>
{
    Optional<Release> findByIdAndDeletedAtIsNull(Long id);
}
