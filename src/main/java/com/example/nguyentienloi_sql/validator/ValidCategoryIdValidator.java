package com.example.nguyentienloi_sql.validator;

import com.example.nguyentienloi_sql.entity.Category;
import com.example.nguyentienloi_sql.validator.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidCategoryIdValidator implements ConstraintValidator <ValidCategoryId, Category>{
    public boolean isValid(Category category, ConstraintValidatorContext context) {
        return category != null && category.getId() != null;
    }
}
