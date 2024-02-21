@regression
Feature: Restful-booker api testing

  Background:
    Given the base URI

  Scenario Outline: Create a token - auth
    When a "<method>" request is made to "<endpoint>" with the below data:
      | username | admin      |
      | password | <password> |
    Then the response "<status_code>"
    And the "token" is generated
    Examples:
      | endpoint | method | status_code | password    |
      | /auth    | post   | 200         | password123 |


  Scenario Outline: Verify a user can be created
    When a "<method>" request with the "<json_body>" is made to "<endpoint>"
    Then the response "<status_code>"
    And the "<response>" body has the below data:
      | firstname   | John |
      | lastname    | Doe  |
      | totalprice  | 111  |
      | depositpaid | true |
    Examples:
      | endpoint | method | status_code | response | json_body  |
      | /booking | post   | 200         | create   | newBooking |

  Scenario Outline: Verify a user can be updated
    When a "<method>" request with the "<json_body>" is made to "<endpoint>"
    Then the response "<status_code>"
    And the "<response>" body has the below data:
      | firstname   | James |
      | lastname    | Brown |
      | totalprice  | 111   |
      | depositpaid | true  |
    Examples:
      | endpoint   | method | status_code | response | json_body     |
      | /booking/1 | put    | 200         | update   | updateBooking |

  Scenario Outline: Verify a user can be partially updated
    When a "<method>" request with the "<body>" is made to "<endpoint>"
    Then the response "<status_code>"
    And the "<response>" body has the below data:
      | firstname   | Mike   |
      | lastname    | Bolton |
      | totalprice  | 111    |
      | depositpaid | true   |
    Examples:
      | endpoint   | method | body              | status_code | response         |
      | /booking/1 | patch  | partUpdateBooking | 200         | partially update |

  Scenario Outline: Verify a user can be deleted
    When a "<method>" request with the "<json_body>" is made to "<endpoint>"
    Then the response "<status_code>"

    Examples:
      | endpoint   | method | status_code | json_body |
      | /booking/1 | delete | 201         |           |


