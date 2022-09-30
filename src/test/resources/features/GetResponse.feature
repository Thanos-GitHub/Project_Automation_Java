Feature: Get response from headers

  Scenario Outline: Get response from headers
    Given User login page
    When User get reponse
      | username                    | password    |
      | buiduythanh270801@gmail.com | Thanh270801 |
    Then User get data "<data1>","<data2>"
    Examples:
      | data1 | data2 |
      | 123 | 456 |