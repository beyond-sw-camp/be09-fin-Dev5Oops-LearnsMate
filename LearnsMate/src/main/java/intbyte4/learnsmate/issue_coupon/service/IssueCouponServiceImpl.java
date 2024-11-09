package intbyte4.learnsmate.issue_coupon.service;

import intbyte4.learnsmate.common.exception.CommonException;
import intbyte4.learnsmate.common.exception.StatusEnum;
import intbyte4.learnsmate.coupon.domain.entity.CouponEntity;
import intbyte4.learnsmate.coupon.service.CouponService;
import intbyte4.learnsmate.issue_coupon.domain.IssueCoupon;
import intbyte4.learnsmate.issue_coupon.domain.dto.IssueCouponDTO;
import intbyte4.learnsmate.issue_coupon.domain.vo.request.IssueCouponRegisterRequestVO;
import intbyte4.learnsmate.issue_coupon.mapper.IssueCouponMapper;
import intbyte4.learnsmate.issue_coupon.repository.IssueCouponRepository;
import intbyte4.learnsmate.member.domain.entity.Member;
import intbyte4.learnsmate.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("issueCouponService")
@RequiredArgsConstructor
public class IssueCouponServiceImpl implements IssueCouponService {

    private final IssueCouponRepository issueCouponRepository;
    private final IssueCouponMapper issueCouponMapper;
    private final MemberService memberService;
    private final CouponService couponService;

    public List<IssueCouponDTO> issueCouponsToStudents(IssueCouponRegisterRequestVO request) {
        List<IssueCouponDTO> issuedCoupons = new ArrayList<>();

        for (Long studentCode : request.getStudentCodes()) {
            Member student = findStudent(studentCode);
            for (Long couponCode : request.getCouponCodes()) {
                IssueCoupon issueCoupon = createAndSaveIssueCoupon(student, couponCode);
                issuedCoupons.add(issueCouponMapper.toDTO(issueCoupon));
            }
        }
        return issuedCoupons;
    }

    @Override
    public List<IssueCouponDTO> findIssuedCouponsByStudent(Long studentCode) {
        List<IssueCoupon> issueCouponList = issueCouponRepository.findAllByStudentAndActiveCoupon(studentCode);
        return issueCouponList.stream()
                .map(issueCouponMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public IssueCouponDTO useIssuedCoupon(Long studentCode, String couponIssuanceCode) {
        IssueCoupon issueCoupon = issueCouponRepository.findByCouponIssuanceCodeAndStudentCode(couponIssuanceCode, studentCode).orElseThrow(() -> new CommonException(StatusEnum.COUPON_NOT_FOUND));

        issueCoupon.useCoupon();
        issueCouponRepository.save(issueCoupon);
        return issueCouponMapper.toDTO(issueCoupon);
    }

    private Member findStudent(Long studentCode) {
        return memberService.findByStudentCode(studentCode);
    }

    private IssueCoupon createAndSaveIssueCoupon(Member student, Long couponCode) {
        CouponEntity coupon = couponService.findByCouponCode(couponCode);
        IssueCoupon issueCoupon = IssueCoupon.createIssueCoupon(coupon, student);
        return issueCouponRepository.save(issueCoupon);
    }
}