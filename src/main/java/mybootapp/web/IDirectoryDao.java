package mybootapp.web;

import java.util.Collection;

import mybootapp.model.Group;
import mybootapp.model.Person;

public interface IDirectoryDao {

	   // récupérer les groupes
	   Collection<Group> findAllGroups();

	   // lire une personne
	   Person findPerson(long id);

	   // lire un groupe et ses personnes
	   Group findGroup(long id);

	   // modification ou ajout d'une nouvelle personne
	   void savePerson(Person p);

	   // modification ou ajout d'une nouvelle personne
	   void saveGroup(Group g);

}