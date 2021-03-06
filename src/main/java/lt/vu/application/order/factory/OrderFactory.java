package lt.vu.application.order.factory;

import lt.vu.application.exception.NotFoundException;
import lt.vu.application.order.service.calculator.PriceCalculator;
import lt.vu.persistence.entities.*;
import lt.vu.persistence.repository.AttributeRepository;
import lt.vu.persistence.repository.PackageOptionRepository;
import lt.vu.web.api.v1.dto.order.PostOrderDTO;
import lt.vu.application.userInfo.factory.UserInfoFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class OrderFactory {

    @Inject
    private PackageOptionRepository packageOptionRepository;

    @Inject
    private AttributeRepository attributeRepository;

    @Inject
    private UserInfoFactory userInfoFactory;

    @Inject
    private PriceCalculator priceCalculator;

    public Order createFromDTO(User sender, PostOrderDTO orderDTO) throws NotFoundException {
        Order order = new Order();

        PackageOption packageOption = this.packageOptionRepository.findOneById(orderDTO.getPackageOptionId());

        order.setSenderInfo(this.userInfoFactory.createFromDTO(orderDTO.getSender()));
        order.setRecipientInfo(this.userInfoFactory.createFromDTO(orderDTO.getRecipient()));
        order.setStatus(OrderStatus.NEW);
        order.setPickupDateTime(orderDTO.getPickUpDate());
        order.setPackageOption(packageOption);
        order.setAttributes(this.attributeRepository.findAllByIds(orderDTO.getAttributes()));
        order.setTotalPrice(this.priceCalculator.calculate(order));
        if (sender != null) {
            order.setSender(sender);
        }

        return order;
    }
}
