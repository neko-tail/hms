package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.UserController;
import net.thmaster.hms.controller.WeightController;
import net.thmaster.hms.model.entity.Weight;
import net.thmaster.hms.model.model.WeightModel;
import net.thmaster.hms.model.req.query.WeightQueryRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author master
 */
@Component
public class WeightModelAssembler extends RepresentationModelAssemblerSupport<Weight, WeightModel> {

    public WeightModelAssembler(
    ) {
        super(WeightController.class, WeightModel.class);
    }

    @NonNull
    @Override
    protected WeightModel instantiateModel(@NonNull Weight entity) {
        return new WeightModel(entity.getWeight(), entity.getDate());
    }

    @NonNull
    @Override
    public WeightModel toModel(@NonNull Weight entity) {
        WeightModel model = instantiateModel(entity);

        Long userId = entity.getUserId();

        model.add(linkTo(methodOn(WeightController.class).get(userId, entity.getId())).withSelfRel())
                .add(linkTo(methodOn(WeightController.class).list(userId, null)).withRel("weights"))
                .add(linkTo(methodOn(WeightController.class).update(userId, entity.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(WeightController.class).delete(userId, entity.getId())).withRel("delete"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return model;
    }

    public CollectionModel<WeightModel> toCollectionModel(
            @NonNull Iterable<? extends Weight> entities,
            Long userId,
            WeightQueryRequest query
    ) {
        CollectionModel<WeightModel> collectionModel = super.toCollectionModel(entities);

        collectionModel.add(linkTo(methodOn(WeightController.class).list(userId, query)).withSelfRel())
                .add(linkTo(methodOn(WeightController.class).create(userId, null)).withRel("create"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return collectionModel;
    }

}

