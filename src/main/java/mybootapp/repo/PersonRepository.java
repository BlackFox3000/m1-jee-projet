package mybootapp.repo;

import mybootapp.model.Person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

	@Query("SELECT p FROM Person p WHERE p.lastname LIKE :lastnameKey")
	Page<Person> findByLastname(@Param("lastnameKey") String lastnameP, Pageable pageable);
	
	@Query("SELECT p FROM Person p JOIN p.groupe WHERE p.groupe.id = :idKey")
	static Page<Person> findByIdGroupe(@Param("idKey") long IdGoupe, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Query("SELECT p FROM Person p LEFT JOIN p.group WHERE p.group.id = :idKey AND p.lastname LIKE :lastnameKey")
	static Page<Person> findByIdGroupeAndPersonLastname(@Param("idKey") long IdGoupe, @Param("lastnameKey") String lastnamePerson, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Query("UPDATE p FROM Person SET p.nom = :nomKey, p.prenom = :prenomKey, p.email = :emailKey,"
			+ " p.siteweb = :sitewebKey, p.dtn = :dtnKey, p.id_groupe = :idGroupeKey WHERE p.id = :idKey")
	public Page<Person> update(@Param("nomKey") String nom, @Param("prenomKey") String prenom,
			@Param("emailKey") String email, @Param("sitewebKey") String siteweb, @Param("dtnKey") String dtn, 
			@Param("idGroupeKey") int idGroupe, @Param("idKey") int id, Pageable pageable);*/
	
	/*@Query(value ="UPDATE Person SET lastname = :lastnameKey, firstname = :firstnameKey, email = :emailKey,"
			+ " website = :websiteKey, birthdate = :birthdateKey, id_group = :idGroupKey WHERE id = :idKey", nativeQuery = true)
	public Page<Person> doUpdate(@Param("lastnameKey") String lastname, @Param("firstnameKey") String firstname,
			@Param("emailKey") String email, @Param("websiteKey") String website, @Param("birthdateKey") String birthdate, 
			@Param("idGroupeKey") int idGroupe, @Param("idKey") int id, Pageable pageable);*/

	EntityManager em = null;
	default <T> T find(Class<T> clazz, Object id) {
		assert false;
		T entity = em.find(clazz, id);
		if (entity == null)
			System.err.println("Entity not found.");

		return entity;
	}

	default <T> T update(T entity) {
		assert false;
		entity = em.merge(entity);
		System.err.println("Entity updated.");
		return entity;
	}

}
