package com.emusicstore.springvalidator;

import com.emusicstore.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by sabin on 2/28/2017.
 */
@Component
public class ProductSpringValidator implements Validator{

    @Override
    public boolean supports(Class<?> arg0) {
        return Product.class.equals(arg0);
    }

    @Override
    public void validate(Object obj, Errors err) {
        Product product =(Product) obj;
        ValidationUtils.rejectIfEmpty(err, "productManufacturer", "product.productManufacturer");

        }
    }

