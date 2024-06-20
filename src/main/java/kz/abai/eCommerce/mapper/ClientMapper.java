package kz.abai.eCommerce.mapper;

import kz.abai.eCommerce.dto.ClientDto;
import kz.abai.eCommerce.entities.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    ClientEntity toEntity(ClientDto clientDto);
    ClientDto toDto(ClientEntity clientEntity);
}
