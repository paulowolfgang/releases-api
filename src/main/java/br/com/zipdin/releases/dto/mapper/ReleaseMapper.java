package br.com.zipdin.releases.dto.mapper;

import br.com.zipdin.releases.dto.request.ReleaseRequestDto;
import br.com.zipdin.releases.dto.response.ReleaseResponseDto;
import br.com.zipdin.releases.entity.Release;
import org.modelmapper.ModelMapper;

public class ReleaseMapper
{
    private static final ModelMapper mapper = new ModelMapper();

    public static Release converter(ReleaseRequestDto request) {
        Release release = mapper.map(request, Release.class);
        release.setSystem(request.getSystem().trim());
        return release;
    }

    public static ReleaseResponseDto converter(Release release)
    {
        return mapper.map(release, ReleaseResponseDto.class);
    }

    public static void copyToProperties(ReleaseRequestDto request, Release release)
    {
        mapper.map(request, release);
    }
}
