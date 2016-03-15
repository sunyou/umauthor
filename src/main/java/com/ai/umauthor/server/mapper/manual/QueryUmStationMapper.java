package com.ai.umauthor.server.mapper.manual;

import java.util.List;

import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.service.mrg.model.QryUmStation;

public interface QueryUmStationMapper {
    List<UmStation> qryStationByOperator(QryUmStation where);
}