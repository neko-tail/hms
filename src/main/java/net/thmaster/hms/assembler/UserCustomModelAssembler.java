package net.thmaster.hms.assembler;

import net.thmaster.hms.controller.UserController;
import net.thmaster.hms.controller.UserCustomController;
import net.thmaster.hms.model.entity.UserCustom;
import net.thmaster.hms.model.model.UserCustomModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author master
 */
@Component
public class UserCustomModelAssembler extends RepresentationModelAssemblerSupport<UserCustom, UserCustomModel> {

    public UserCustomModelAssembler() {
        super(UserCustomController.class, UserCustomModel.class);
    }

    @NonNull
    @Override
    protected UserCustomModel instantiateModel(@NonNull UserCustom entity) {
        return new UserCustomModel(entity.getCalorieLimit(), entity.getInitWeight(), entity.getTargetWeight());
    }

    @NonNull
    @Override
    public UserCustomModel toModel(@NonNull UserCustom entity) {
        UserCustomModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(UserCustomController.class).get(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(UserCustomController.class).update(entity.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(UserController.class).get(entity.getUserId())).withRel("user"))
        ;

        return model;
    }

}

