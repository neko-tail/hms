package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.FoodController;
import net.thmaster.hms.model.entity.Food;
import net.thmaster.hms.model.model.FoodModel;
import net.thmaster.hms.model.req.query.FoodQueryRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author master
 */
@Component
public class FoodModelAssembler extends RepresentationModelAssemblerSupport<Food, FoodModel> {

    public FoodModelAssembler() {
        super(FoodController.class, FoodModel.class);
    }

    @NonNull
    @Override
    protected FoodModel instantiateModel(Food entity) {
        return new FoodModel(entity.getId(), entity.getName(), entity.getDesc(), entity.getCalorie(), entity.getImg());
    }

    @NonNull
    @Override
    public CollectionModel<FoodModel> toCollectionModel(@NonNull Iterable<? extends Food> entities) {
        return toCollectionModel(entities, null);
    }

    @NonNull
    @Override
    public FoodModel toModel(@NonNull Food entity) {
        FoodModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(FoodController.class).get(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(FoodController.class).list(null)).withRel("foods"))
                .add(linkTo(methodOn(FoodController.class).update(entity.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(FoodController.class).delete(entity.getId())).withRel("delete"));

        return model;
    }

    @NonNull
    public CollectionModel<FoodModel> toCollectionModel(Iterable<? extends Food> entities, FoodQueryRequest query) {
        List<FoodModel> modelList = StreamSupport.stream(entities.spliterator(), false).map(this::toModel).toList();

        CollectionModel<FoodModel> collectionModel = CollectionModel.of(modelList);

        collectionModel.add(linkTo(methodOn(FoodController.class).list(query)).withSelfRel())
                .add(linkTo(methodOn(FoodController.class).create(null)).withRel("create"));

        return collectionModel;
    }

}
