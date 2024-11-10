package intbyte4.learnsmate.lecture_by_student.service;

import intbyte4.learnsmate.lecture_by_student.domain.dto.LectureByStudentDTO;

import java.util.List;

public interface LectureByStudentService {
    // 학생별 모든 강의 조회
    List<LectureByStudentDTO> findByStudentCode(Long studentCode);
}