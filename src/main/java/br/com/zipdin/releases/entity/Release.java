package br.com.zipdin.releases.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "release")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Release
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String system;

    @Column(nullable = false, length = 30)
    private String version;

    @ElementCollection
    private List<String> commits;

    @Lob
    private String notes;

    @Column(name = "user_name", nullable = false, length = 100)
    private String user;

    @Column(length = 100)
    private String userUpdate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime releasedAt;

    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate()
    {
        this.releasedAt = LocalDateTime.now();
    }
}
