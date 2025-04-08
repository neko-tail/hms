package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.BlogController;
import net.thmaster.hms.model.entity.Blog;
import net.thmaster.hms.model.model.BlogModel;
import net.thmaster.hms.model.req.query.BlogQueryRequest;
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
public class BlogModelAssembler extends RepresentationModelAssemblerSupport<Blog, BlogModel> {

    public BlogModelAssembler() {
        super(BlogController.class, BlogModel.class);
    }

    @NonNull
    @Override
    protected BlogModel instantiateModel(Blog entity) {
        return new BlogModel(entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getContent());
    }

    @NonNull
    @Override
    public CollectionModel<BlogModel> toCollectionModel(@NonNull Iterable<? extends Blog> entities) {
        return toCollectionModel(entities, null);
    }

    @NonNull
    @Override
    public BlogModel toModel(@NonNull Blog entity) {
        BlogModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(BlogController.class).get(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(BlogController.class).list(null)).withRel("blogs"))
                .add(linkTo(methodOn(BlogController.class).update(entity.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(BlogController.class).delete(entity.getId())).withRel("delete"));

        return model;
    }

    @NonNull
    public CollectionModel<BlogModel> toCollectionModel(Iterable<? extends Blog> entities, BlogQueryRequest query) {
        List<BlogModel> modelList = StreamSupport.stream(entities.spliterator(), false).map(this::toModel).toList();

        CollectionModel<BlogModel> collectionModel = CollectionModel.of(modelList);

        collectionModel.add(linkTo(methodOn(BlogController.class).list(query)).withSelfRel())
                .add(linkTo(methodOn(BlogController.class).create(null)).withRel("create"));

        return collectionModel;
    }

}
