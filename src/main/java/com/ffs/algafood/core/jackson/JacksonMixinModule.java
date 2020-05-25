package com.ffs.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ffs.algafood.api.model.mixin.CityMixin;
import com.ffs.algafood.api.model.mixin.KitchenMixin;
import com.ffs.algafood.api.model.mixin.RestaurantMixin;
import com.ffs.algafood.domain.model.City;
import com.ffs.algafood.domain.model.Kitchen;
import com.ffs.algafood.domain.model.Restaurant;
import org.springframework.stereotype.Component;

/**
 *
 * @author francisco
 */
@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
        setMixInAnnotation(Kitchen.class, KitchenMixin.class);
        setMixInAnnotation(City.class, CityMixin.class);
    }
}
