package intbyte4.learnsmate.blacklist.domain.vo.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseFindBlacklistVO {

    private Long blackCode;
    private String blackReason;
    private LocalDateTime createdAt;
    private Long memberCode;
    private Long adminCode;
}