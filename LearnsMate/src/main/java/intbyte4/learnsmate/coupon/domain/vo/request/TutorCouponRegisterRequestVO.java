package intbyte4.learnsmate.coupon.domain.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TutorCouponRegisterRequestVO {
    @JsonProperty("coupon_code")
    private Long couponCode;

    @JsonProperty("coupon_name")
    private String couponName;

    @JsonProperty("coupon_discount_rate")
    private Integer couponDiscountRate;

    @JsonProperty("coupon_expire_date")
    private LocalDateTime couponExpireDate;
}