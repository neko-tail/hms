package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.DietController;
import net.thmaster.hms.controller.FoodController;
import net.thmaster.hms.controller.UserController;
import net.thmaster.hms.model.dto.DietDTO;
import net.thmaster.hms.model.model.DietModel;
import net.thmaster.hms.model.req.query.DietQueryRequest;
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
public class DietModelAssembler extends RepresentationModelAssemblerSupport<DietDTO, DietModel> {

    public DietModelAssembler() {
        super(DietController.class, DietModel.class);
    }

    @NonNull
    @Override
    protected DietModel instantiateModel(@NonNull DietDTO dto) {
        return new DietModel(
                dto.getType().getName(),
                dto.getCount(),
                dto.getTime(),
                dto.getFood().getName()
        );
    }

    @NonNull
    @Override
    public CollectionModel<DietModel> toCollectionModel(@NonNull Iterable<? extends DietDTO> entities) {
        return super.toCollectionModel(entities);
    }

    @NonNull
    @Override
    public DietModel toModel(@NonNull DietDTO entity) {
        DietModel model = instantiateModel(entity);

        Long userId = entity.getUserId();

        model.add(linkTo(methodOn(DietController.class).get(userId, entity.getId())).withSelfRel())
                .add(linkTo(methodOn(DietController.class).list(userId, null)).withRel("diets"))
                .add(linkTo(methodOn(DietController.class).update(userId, entity.getId(), null))
                        .withRel("update"))
                .add(linkTo(methodOn(DietController.class).delete(userId, entity.getId())).withRel("delete"))
                .add(linkTo(methodOn(FoodController.class).get(entity.getFood().getId())).withRel("food"))
                .add(linkTo(methodOn(UserController.class).get(entity.getUserId())).withRel("user"))
        ;

        return model;
    }

    @NonNull
    public CollectionModel<DietModel> toCollectionModel(
            @NonNull Iterable<? extends DietDTO> entities,
            @NonNull Long userId,
            DietQueryRequest query
    ) {
        CollectionModel<DietModel> collectionModel = super.toCollectionModel(entities);

        collectionModel.add(linkTo(methodOn(DietController.class).list(userId, query)).withSelfRel())
                .add(linkTo(methodOn(DietController.class).create(userId, null)).withRel("create"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return collectionModel;
    }

}

