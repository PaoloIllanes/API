Feature: Login

  Scenario: Login Facebook

    Given i have access to "facebook"
    When i type the email: upb@upb.com
    And i type the password: 123456
    And i click on login button
    Then i should be logged
