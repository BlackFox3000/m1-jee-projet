package mybootapp.web;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import mybootapp.model.Person;

@Service
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname",
                "person.firstname");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname",
                "person.lastname");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "person.email");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website",
                "person.website");

        ValidationUtils.rejectIfEmpty(errors, "birthdate",
                "person.birthdate");


    }

}