package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.ExerciseController;
import net.thmaster.hms.controller.UserController;
import net.thmaster.hms.model.dto.ExerciseDTO;
import net.thmaster.hms.model.model.ExerciseModel;
import net.thmaster.hms.model.req.query.ExerciseQueryRequest;
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
public class ExerciseModelAssembler extends RepresentationModelAssemblerSupport<ExerciseDTO, ExerciseModel> {

    public ExerciseModelAssembler() {
        super(ExerciseController.class, ExerciseModel.class);
    }

    @NonNull
    @Override
    protected ExerciseModel instantiateModel(@NonNull ExerciseDTO entity) {
        return new ExerciseModel(entity.getId(),
                entity.getUserId(),
                entity.getMovement().getName(),
                entity.getCount(),
                entity.getWeight()
        );
    }

    @NonNull
    @Override
    public CollectionModel<ExerciseModel> toCollectionModel(@NonNull Iterable<? extends ExerciseDTO> entities) {
        return super.toCollectionModel(entities);
    }

    @NonNull
    @Override
    public ExerciseModel toModel(@NonNull ExerciseDTO entity) {
        ExerciseModel model = instantiateModel(entity);

        Long userId = entity.getUserId();
        Long exerciseId = entity.getId();

        model.add(linkTo(methodOn(ExerciseController.class).get(userId, exerciseId)).withSelfRel())
                .add(linkTo(methodOn(ExerciseController.class).list(userId, null)).withRel("exercises"))
                .add(linkTo(methodOn(ExerciseController.class).update(userId, exerciseId, null))
                        .withRel("update"))
                .add(linkTo(methodOn(ExerciseController.class).delete(userId, exerciseId))
                        .withRel("delete"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return model;
    }

    @NonNull
    public CollectionModel<ExerciseModel> toCollectionModel(
            @NonNull Iterable<? extends ExerciseDTO> entities,
            @NonNull Long userId,
            ExerciseQueryRequest query
    ) {
        CollectionModel<ExerciseModel> collectionModel = super.toCollectionModel(entities);

        collectionModel.add(linkTo(methodOn(ExerciseController.class).list(userId, query)).withSelfRel())
                .add(linkTo(methodOn(ExerciseController.class).create(userId, null)).withRel("create"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return collectionModel;
    }

}

