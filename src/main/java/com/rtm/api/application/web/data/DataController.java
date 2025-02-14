package com.rtm.api.application.web.data;

import com.rtm.api.application.dto.filter.DataFilterDTO;
import com.rtm.api.application.dto.request.DataRequestDTO;
import com.rtm.api.application.dto.response.DataResponseDTO;
import com.rtm.api.domain.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DataController implements DataApi
{
    private final DataService dataService;
    
    @Override
    public List<DataResponseDTO> getData(Integer page, Integer size, DataFilterDTO filter ) 
    {
        return dataService.findAll( PageRequest.of( page, size, Sort.by( Sort.Direction.DESC, "id" ) ), filter );    
    }
    
    @Override
    public List<DataResponseDTO> getAllData() {
        return dataService.findAll();
    }
    
    @Override
    public List<DataResponseDTO> getPermittedData( DataFilterDTO filter ) {
        return dataService.getFilteredData( filter );
    }
    
    @Override
    public void save(DataRequestDTO dto)
    {
        dataService.save( dto );    
    }
    
    @Override
    public void update(DataRequestDTO dto) 
    {
        dataService.updateData( dto );   
    }
    
    @Override
    public List<DataResponseDTO> getDataFilter( DataFilterDTO filter ) 
    {
        return dataService.getFilteredData( filter );
    }
}
