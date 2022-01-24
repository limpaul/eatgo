package kr.co.fastcampus.eatgo.eatgo.application;

import kr.co.fastcampus.eatgo.application.RegionService;
import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RegionServiceTest {

    private RegionService regionService;
    @MockBean
    private RegionRepository regionRepository;

    @BeforeEach
    public void setup(){
        regionService = new RegionService(regionRepository);
    }
    @Test
    public void getRegions(){
        when(regionRepository.findAll())
                .thenReturn(Arrays.asList(Region.builder().name("Seoul").build()));

        List<Region> regions = regionRepository.findAll();
        Region region = regions.get(0);
        assertEquals(region.getName(), "Seoul");
    }

}