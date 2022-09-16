package gama.lundegaard.simple_back.process.service;

import gama.lundegaard.simple_back.process.database.entity.CRequestType;
import gama.lundegaard.simple_back.process.database.entity.Request;

import java.util.List;

public interface RequestService {

    List<CRequestType> getCodeBook();
    void newRequest(Request newRequest);
}
