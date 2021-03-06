package lt.vu.application.userInfo.factory;

import lt.vu.application.address.factory.AddressFactory;
import lt.vu.persistence.entities.UserInfo;
import lt.vu.web.api.v1.dto.userInfo.UserInfoDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserInfoFactory {

    @Inject
    private AddressFactory addressFactory;

    public UserInfo createFromDTO(UserInfoDTO userData) {
        UserInfo userInfo = new UserInfo();

        userInfo.setFirstName(userData.getFirstName());
        userInfo.setLastName(userData.getLastName());
        userInfo.setEmail(userData.getEmail());
        userInfo.setPhoneNumber(userData.getPhoneNumber());
        userInfo.setAddress(addressFactory.createFromDTO(userData.getAddress()));

        return userInfo;
    }
}
