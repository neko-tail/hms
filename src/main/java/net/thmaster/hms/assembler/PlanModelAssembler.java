package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.ExerciseController;
import net.thmaster.hms.controller.PlanController;
import net.thmaster.hms.controller.UserController;
import net.thmaster.hms.model.entity.Plan;
import net.thmaster.hms.model.model.PlanModel;
import net.thmaster.hms.model.req.query.PlanQueryRequest;
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
public class PlanModelAssembler extends RepresentationModelAssemblerSupport<Plan, PlanModel> {


    public PlanModelAssembler() {
        super(PlanController.class, PlanModel.class);
    }

    @NonNull
    @Override
    protected PlanModel instantiateModel(@NonNull Plan entity) {
        return new PlanModel(entity.getName(),
                entity.getDesc(),
                entity.getCreateTime(),
                entity.getFinTime(),
                entity.isFin()
        );
    }

    @NonNull
    @Override
    public CollectionModel<PlanModel> toCollectionModel(@NonNull Iterable<? extends Plan> entities) {
        return super.toCollectionModel(entities);
    }

    @NonNull
    @Override
    public PlanModel toModel(@NonNull Plan entity) {
        PlanModel model = instantiateModel(entity);

        Long userId = entity.getUserId();

        model.add(linkTo(methodOn(PlanController.class).get(userId, entity.getId())).withSelfRel())
                .add(linkTo(methodOn(PlanController.class).list(userId, null)).withRel("plans"))
                .add(linkTo(methodOn(PlanController.class).update(userId, entity.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(PlanController.class).finish(userId, entity.getId())).withRel("finish"))
                .add(linkTo(methodOn(PlanController.class).delete(userId, entity.getId())).withRel("delete"))
                .add(linkTo(methodOn(ExerciseController.class).list(userId, entity.getId(), null)).withRel("exercises"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return model;
    }

    public CollectionModel<PlanModel> toCollectionModel(
            @NonNull Iterable<? extends Plan> entities,
            Long userId,
            PlanQueryRequest query
    ) {
        CollectionModel<PlanModel> collectionModel = super.toCollectionModel(entities);

        collectionModel.add(linkTo(methodOn(PlanController.class).list(userId, query)).withSelfRel())
                .add(linkTo(methodOn(PlanController.class).create(userId, null)).withRel("create"))
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        ;

        return collectionModel;
    }

}

