package gama.lundegaard.simple_back.process.service;

import gama.lundegaard.simple_back.process.database.entity.CRequestType;
import gama.lundegaard.simple_back.process.database.entity.Request;
import gama.lundegaard.simple_back.process.database.repository.CRequestTypeRepository;
import gama.lundegaard.simple_back.process.database.repository.RequestRepository;
import gama.lundegaard.simple_back.web.dto.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RequestServiceImp implements RequestService{

    @Autowired
    private RequestRepository requestRep;
    @Autowired
    private CRequestTypeRepository typeCodebookRep;

    public void newRequest(Request newRequest){
        if(typeCodebookRep.existsById(newRequest.getType().getId()))
            requestRep.save(newRequest);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Request type not found");
    }
    public List<CRequestType> getCodeBook(){
        return typeCodebookRep.findAll();
    }
}
