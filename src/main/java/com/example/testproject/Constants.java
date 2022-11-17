package com.example.testproject;

public class Constants {

  public static class PersonController {

    public static final String TAG = "Person Controller";
    public static final String TAG_DESCRIPTION = "Controller that provides CRUD functionality for person";
    public static final String GET_PERSON_BY_ID_SUMMARY = "Retrieve person by id";
    public static final String GET_PERSON_BY_ID_DESCRIPTION = "Retrieve a certain person from a database by id";
    public static final String GET_ALL_PEOPLE = "Retrieve a list of all people";
    public static final String GET_ALL_PEOPLE_DESCRIPTION = "Retrieve a list of all people with age number";
    public static final String CREATE_PERSON_SUMMARY = "Create new person";
    public static final String CREATE_PERSON_DESCRIPTION = "Create new person using passed title and  body";
    public static final String DELETE_PERSON_BY_ID_SUMMARY = "Delete person by id";
    public static final String DELETE_PERSON_BY_ID_DESCRIPTION = "If no such person than message will be provided.";

    public static final String PERSON_NOT_FOUND = "Person not found";
    public static final String PERSON_SUCCESSFULLY_DELETED = "Person successfully deleted";
    public static final String PERSON_SUCCESSFULLY_CREATED = "Created person by id";
    public static final String RETRIEVE_PERSON_BY_ID = "Retrieve person by id";
    public static final String RETRIEVE_PEOPLE_LIST = "Retrieve people list";

  }

  public static class Informer {

    public static final String HTTP_STATUS_OK = "200";
    public static final String HTTP_STATUS_NO_CONTENT = "204";
    public static final String HTTP_STATUS_CREATED = "201";
    public static final String HTTP_STATUS_NOT_FOUND = "404";
  }
}
