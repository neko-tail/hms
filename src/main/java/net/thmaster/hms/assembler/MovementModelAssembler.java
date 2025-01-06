package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.MovementController;
import net.thmaster.hms.model.entity.Movement;
import net.thmaster.hms.model.model.MovementModel;
import net.thmaster.hms.model.req.query.MovementQueryRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author master
 */
@Component
public class MovementModelAssembler extends RepresentationModelAssemblerSupport<Movement, MovementModel> {

    public MovementModelAssembler() {
        super(MovementController.class, MovementModel.class);
    }

    @NonNull
    @Override
    protected MovementModel instantiateModel(@NonNull Movement entity) {
        return new MovementModel(entity.getName(), entity.getDesc(), entity.getCalorie(), entity.getImg());
    }

    @NonNull
    @Override
    public CollectionModel<MovementModel> toCollectionModel(@NonNull Iterable<? extends Movement> entities) {
        return toCollectionModel(entities, null);
    }

    @NonNull
    @Override
    public MovementModel toModel(@NonNull Movement entity) {
        MovementModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(MovementController.class).get(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(MovementController.class).list(null)).withRel("movements"))
                .add(linkTo(methodOn(MovementController.class).update(entity.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(MovementController.class).delete(entity.getId())).withRel("delete"));

        return model;
    }

    public CollectionModel<MovementModel> toCollectionModel(
            @NonNull Iterable<? extends Movement> entities,
            MovementQueryRequest query
    ) {
        CollectionModel<MovementModel> collectionModel =
                CollectionModel.of(StreamSupport.stream(entities.spliterator(), false).map(this::toModel).toList());

        collectionModel.add(linkTo(methodOn(MovementController.class).list(query)).withSelfRel())
                .add(linkTo(methodOn(MovementController.class).create(null)).withRel("create"));

        return collectionModel;
    }

}

