package lt.vu.web.api.v1.dto.order;

import lombok.Getter;
import lombok.Setter;
import lt.vu.web.api.v1.dto.userInfo.PostUserInfoDTO;

import java.util.Date;

@Getter @Setter
public class PostOrderDTO {

    private PostUserInfoDTO sender;

    private PostUserInfoDTO recipient;

    private int packageOptionId;

    private Date pickUpDate;
}