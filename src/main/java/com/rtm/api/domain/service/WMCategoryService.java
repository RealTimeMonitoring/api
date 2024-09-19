package com.rtm.api.domain.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rtm.api.application.dto.request.WMCategoryDTO;
import com.rtm.api.domain.mapper.WMCategoryMapper;
import com.rtm.api.domain.mapper.WMDataMapper;
import com.rtm.api.domain.model.WMCategory;
import com.rtm.api.infra.repository.WMCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WMCategoryService
{
	private final WMCategoryMapper wmCategoryMapper = Mappers.getMapper( WMCategoryMapper.class );

	private final RestTemplate restTemplate;
	private final WMCategoryRepository categoryRepository;

	public List<WMCategory> getCategories()
	{
		ResponseEntity<String> categoriesJson = restTemplate.getForEntity( "http://177.44.248.13:8080/WaterManager/productID.jsp?FORMAT=JSON", String.class );

		if ( categoriesJson.hasBody() )
		{
			ArrayList<WMCategoryDTO> values = new Gson().fromJson( categoriesJson.getBody().replace( "\\", "\\\\" ).trim(), TypeToken.getParameterized( ArrayList.class, WMCategoryDTO.class ).getType() );

			List<WMCategory> categories = values.stream().map( wmCategoryMapper::dtoToModel ).toList();

			categoryRepository.saveAll( categories );

			return categories;
		}

		return List.of();
	}
}
