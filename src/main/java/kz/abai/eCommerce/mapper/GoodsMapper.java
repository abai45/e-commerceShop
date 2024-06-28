package kz.abai.eCommerce.mapper;

import kz.abai.eCommerce.dto.GoodDto;
import kz.abai.eCommerce.entities.GoodsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GoodsMapper {
    GoodsMapper INSTANCE = Mappers.getMapper(GoodsMapper.class);
    GoodDto toDto(GoodsEntity goodsEntity);
    GoodsEntity toEntity(GoodDto goodDto);
}
