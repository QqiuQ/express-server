package com.team24.express.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team24.express.common.AjaxResult;
import com.team24.express.entity.Station;
import com.team24.express.entity.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


public interface StationService {
    Station selectByStationname(String username);

    List<Station> getStationList();

    boolean deleteById( Long id);
    boolean deleteBatchIds( List<Long> ids);
    int insert(Station station);

    int update(Station station);


    List<Station> queryList();
}
