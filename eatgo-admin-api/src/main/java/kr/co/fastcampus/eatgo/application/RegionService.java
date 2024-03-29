package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    public Region addRegion(String seoul) {
        Region region = Region.builder().name("Seoul").build();
        regionRepository.save(region);
        return region;
    }
}
