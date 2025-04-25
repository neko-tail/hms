package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.CommentController;
import net.thmaster.hms.model.entity.Comment;
import net.thmaster.hms.model.model.CommentModel;
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
public class CommentModelAssembler extends RepresentationModelAssemblerSupport<Comment, CommentModel> {

    public CommentModelAssembler() {
        super(CommentController.class, CommentModel.class);
    }

    @NonNull
    @Override
    protected CommentModel instantiateModel(Comment entity) {
        return new CommentModel(entity.getId(), entity.getBlogId(), entity.getUserId(), entity.getContent());
    }

    @NonNull
    @Override
    public CollectionModel<CommentModel> toCollectionModel(@NonNull Iterable<? extends Comment> entities) {
        return super.toCollectionModel(entities);
    }

    @NonNull
    @Override
    public CommentModel toModel(@NonNull Comment entity) {
        CommentModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(CommentController.class).get(entity.getBlogId(), entity.getUserId(), entity.getId())).withSelfRel())
                .add(linkTo(methodOn(CommentController.class).list(entity.getBlogId(), entity.getUserId())).withRel("comments"))
                .add(linkTo(methodOn(CommentController.class).update(entity.getBlogId(), entity.getUserId(), entity.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(CommentController.class).delete(entity.getBlogId(), entity.getUserId(), entity.getId())).withRel("delete"));

        return model;
    }

    @NonNull
    public CollectionModel<CommentModel> toCollectionModel(
            Iterable<? extends Comment> entities,
            @NonNull Long blogId,
            @NonNull Long userId
    ) {
        List<CommentModel> modelList = StreamSupport.stream(entities.spliterator(), false).map(this::toModel).toList();

        CollectionModel<CommentModel> collectionModel = CollectionModel.of(modelList);

        collectionModel.add(linkTo(methodOn(CommentController.class).list(blogId, userId)).withSelfRel())
                .add(linkTo(methodOn(CommentController.class).create(blogId, userId, null)).withRel("create"));

        return collectionModel;
    }

}
