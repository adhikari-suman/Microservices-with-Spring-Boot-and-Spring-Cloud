package np.com.suman_adhikari.microservices.core.product.services;

import np.com.suman_adhikari.api.core.product.Product;
import np.com.suman_adhikari.microservices.core.product.persistence.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({@Mapping(target = "serviceAddress", ignore = true)})
    Product entityToApi(ProductEntity entity);

    @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "version", ignore = true)})
    ProductEntity apiToEntity(Product api);
}