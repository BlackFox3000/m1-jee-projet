package mybootapp.repo;

import mybootapp.model.Groupe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Groupe, Long> {
	@Query("SELECT g FROM Groupe g WHERE g.name LIKE :key")
	Page<Groupe> find(@Param("key") String nameG, Pageable pageable);
}
