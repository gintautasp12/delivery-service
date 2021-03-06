package lt.vu.application.user.factory;

import lt.vu.application.address.factory.AddressFactory;
import lt.vu.application.security.service.PasswordHasher;
import lt.vu.persistence.entities.User;
import lt.vu.persistence.entities.UserRole;
import lt.vu.web.api.v1.dto.security.PostRegisterDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Date;

@RequestScoped
public class UserFactory {

    @Inject
    private PasswordHasher passwordHasher;

    @Inject
    private AddressFactory addressFactory;

    public User create(PostRegisterDTO registerDTO) {
        User user = new User();

        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(this.passwordHasher.hash(registerDTO.getPassword()));
        user.setAddress(this.addressFactory.createFromDTO(registerDTO.getAddress()));
        user.setRoles(Collections.singletonList(UserRole.USER));
        user.setLastLogin(new Date());

        return user;
    }
}
