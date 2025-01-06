package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.*;
import net.thmaster.hms.model.dto.UserDTO;
import net.thmaster.hms.model.entity.UserInfo;
import net.thmaster.hms.model.model.UserModel;
import net.thmaster.hms.model.req.query.UserQueryRequest;
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
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @NonNull
    @Override
    protected UserModel instantiateModel(@NonNull UserDTO entity) {
        UserModel userModel = new UserModel();
        userModel.setUsername(entity.getUsername());
        userModel.setEmail(entity.getEmail());
        userModel.setPhoneNumber(entity.getPhoneNumber());

        UserInfo info = entity.getInfo();
        userModel.setNickname(info.getNickName());
        userModel.setAge(info.getAge());
        userModel.setGender(info.getGender());
        userModel.setGenderName(info.getGenderName());
        userModel.setHeight(info.getHeight());
        userModel.setWeight(info.getWeight());

        return userModel;
    }

    @NonNull
    @Override
    public CollectionModel<UserModel> toCollectionModel(@NonNull Iterable<? extends UserDTO> entities) {
        return toCollectionModel(entities, null);
    }

    @NonNull
    @Override
    public UserModel toModel(@NonNull UserDTO entity) {
        UserModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(UserController.class).get(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(UserController.class).update(entity.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(UserController.class).delete(entity.getId())).withRel("delete"))
                .add(linkTo(methodOn(MonitorController.class).get(entity.getId())).withRel("monitor"))
                .add(linkTo(methodOn(UserCustomController.class).get(entity.getId())).withRel("custom"))
                .add(linkTo(methodOn(WeightController.class).list(entity.getId(), null)).withRel("weights"))
                .add(linkTo(methodOn(DietController.class).list(entity.getId(), null)).withRel("diets"))
                .add(linkTo(methodOn(PlanController.class).list(entity.getId(), null)).withRel("plans"))
        ;

        return model;
    }

    public CollectionModel<UserModel> toCollectionModel(
            @NonNull Iterable<? extends UserDTO> entities,
            UserQueryRequest query
    ) {
        CollectionModel<UserModel> collectionModel = super.toCollectionModel(entities);

        collectionModel.add(linkTo(methodOn(UserController.class).list(query)).withSelfRel())
                .add(linkTo(methodOn(UserController.class).register(null)).withRel("register"));

        return collectionModel;
    }

}

