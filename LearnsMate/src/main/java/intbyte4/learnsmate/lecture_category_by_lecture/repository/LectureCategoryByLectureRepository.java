package intbyte4.learnsmate.lecture_category_by_lecture.repository;

import intbyte4.learnsmate.lecture_category_by_lecture.domain.entity.LectureCategoryByLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureCategoryByLectureRepository extends JpaRepository<LectureCategoryByLecture, Long> {
}