package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.ExerciseController;
import net.thmaster.hms.controller.PlanController;
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
        return new ExerciseModel(entity.getMovement().getName(), entity.getCount());
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
        Long planId = entity.getPlanId();
        Long exerciseId = entity.getId();

        model.add(linkTo(methodOn(ExerciseController.class).get(userId, planId, exerciseId)).withSelfRel())
                .add(linkTo(methodOn(ExerciseController.class).list(userId, planId, null)).withRel("exercises"))
                .add(linkTo(methodOn(ExerciseController.class).update(userId, planId, exerciseId, null))
                        .withRel("update"))
                .add(linkTo(methodOn(ExerciseController.class).delete(userId, planId, exerciseId))
                        .withRel("delete"))
                .add(linkTo(methodOn(PlanController.class).get(userId, planId)).withRel("plan"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return model;
    }

    @NonNull
    public CollectionModel<ExerciseModel> toCollectionModel(
            @NonNull Iterable<? extends ExerciseDTO> entities,
            @NonNull Long userId,
            @NonNull Long planId,
            ExerciseQueryRequest query
    ) {
        CollectionModel<ExerciseModel> collectionModel = super.toCollectionModel(entities);

        collectionModel.add(linkTo(methodOn(ExerciseController.class).list(userId, planId, query)).withSelfRel())
                .add(linkTo(methodOn(ExerciseController.class).create(userId, planId, null)).withRel("create"))
                .add(linkTo(methodOn(PlanController.class).get(userId, planId)).withRel("plan"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return collectionModel;
    }

}

