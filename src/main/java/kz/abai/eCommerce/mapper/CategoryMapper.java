package kz.abai.eCommerce.mapper;

import kz.abai.eCommerce.dto.CategoryDto;
import kz.abai.eCommerce.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto toDto(CategoryEntity categoryEntity);
    CategoryEntity toEntity(CategoryDto categoryDto);
}

