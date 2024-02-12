Feature: Regres api testing

  Background:
    Given the base URI

  Scenario Outline: Verify users' list
    When a "get" request is made to "<endpoint>"
    Then the response "<status_code>"
    Examples:
      | endpoint | status_code |
      | /users   | 200         |

  Scenario Outline: Verify a single users
    When a "get" request is made to "<endpoint>"
    Then the response "<status_code>"
    Examples:
      | endpoint | status_code |
      | /user/2  | 200         |

  Scenario Outline: Verify a single user is not found
    When a "get" request is made to "<endpoint>"
    Then the response "<status_code>"
    Examples:
      | endpoint | status_code |
      | /user/23 | 404         |

  Scenario Outline: Verify a user can be created and updated
    When a "<method>" request is made to "<endpoint>" with the below data:
      | name | Sam   |
      | job  | <job> |
    Then the response "<status_code>"
    Examples:
      | endpoint | method | status_code | job       |
      | /users   | post   | 201         | engineer  |
      | /users/2 | put    | 200         | developer |
      | /users/2 | patch  | 200         | manager   |

  Scenario Outline: Verify the user 2 can be deleted
    When a "delete" request is made to "<endpoint>"
    Then the response "<status_code>"
    Examples:
      | endpoint | status_code |
      | /users/2 | 204         |

  Scenario Outline: Verify a user can be successfully registered
    When a "<method>" request is made to "<endpoint>" with the below data:
      | email    | <email>    |
      | password | <password> |
    Then the response "<status_code>"
    And the body has "<id>" and "<token>"
    Examples:
      | endpoint  | method | status_code | email              | password | id | token             |
      | /register | post   | 200         | eve.holt@reqres.in | pistol   | 4  | QpwL5tke4Pnpja7X4 |

  Scenario Outline: Verify a user cannot be successfully registered
    When a "<method>" request is made to "<endpoint>" with the below data:
      | email | <email> |
    Then the response "<status_code>"
    And the response body has the below data:
      | error | Missing password |
    Examples:
      | endpoint  | method | status_code | email       |
      | /register | post   | 400         | sydney@fife |

  Scenario Outline: Verify a user can be successfully login
    When a "<method>" request is made to "<endpoint>" with the below data:
      | email    | <email>    |
      | password | <password> |
    Then the response "<status_code>"
    And the response body has the below data:
      | <key> | <value> |
    Examples:
      | endpoint | method | status_code | email              | password   | key   | value             |
      | /login   | post   | 200         | eve.holt@reqres.in | cityslicka | token | QpwL5tke4Pnpja7X4 |
      | /login   | post   | 400         | peter@klaven       |            | error | Missing password  |