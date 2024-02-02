Feature: Regres api

  Background:
   Given the base URI

  Scenario Outline: Verify users' list
    When a "get" request is made to "<endpoint>"
    Then get the response "<status_code>"
    Examples:
      | endpoint | status_code |
      |  /users   |      200       |

  Scenario Outline: Verify a single users
    When a "get" request is made to "<endpoint>"
    Then get the response "<status_code>"
    Examples:
      | endpoint | status_code |
      |  /user/1   |      200   |

  Scenario Outline: Verify a single users not found
    When a "get" request is made to "<endpoint>"
    Then get the response "<status_code>"
    Examples:
      | endpoint | status_code |
      |  /user/23   |      404   |

