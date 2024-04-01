package uz.example.foodstore.utils;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum CacheName {
    @FieldNameConstants.Include PRODUCT,
    @FieldNameConstants.Include PRODUCT_BY_CATEGORY_ID
}
