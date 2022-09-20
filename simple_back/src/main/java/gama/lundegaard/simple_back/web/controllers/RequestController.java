package gama.lundegaard.simple_back.web.controllers;

import gama.lundegaard.simple_back.configuration.CustomResponseEntityExceptionHandler;
import gama.lundegaard.simple_back.process.database.entity.Request;
import gama.lundegaard.simple_back.process.service.RequestService;
import gama.lundegaard.simple_back.web.dto.RequestDTO;
import gama.lundegaard.simple_back.web.dto.RequestTypeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/type")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestTypeDTO> getCodeBook(){
        return requestService.getCodeBook().stream()
                .map(object -> modelMapper.map(object, RequestTypeDTO.class)).toList();
    }

    @PostMapping(value = "/request")
    @ResponseStatus(HttpStatus.CREATED)
    public void newRequest( @Valid @RequestBody RequestDTO newRequest){
        requestService.newRequest(modelMapper.map(newRequest, Request.class));
    }
}
