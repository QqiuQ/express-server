package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.entity.Station;
import com.team24.express.entity.User;
import com.team24.express.mapper.StationMapper;
import com.team24.express.service.StationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Resource
    private StationMapper stationMapper;

    @Override
    public Station selectByStationname(String username) {
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return stationMapper.selectOne(wrapper);
    }

    @Override
    public List<Station> getStationList() {
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        return stationMapper.selectList(wrapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return stationMapper.deleteById(id) > 0;
    }
    public boolean deleteBatchIds(List<Long> ids) {
        return stationMapper.deleteBatchIds(ids) > 0;
    }
    @Override
    public int insert(Station station) {
        return stationMapper.insert(station);
    }

    @Override
    public int update(Station station) {
        return stationMapper.updateById(station);
    }

    @Override
    public List<Station> queryList() {
        return stationMapper.selectList();
    }
}
