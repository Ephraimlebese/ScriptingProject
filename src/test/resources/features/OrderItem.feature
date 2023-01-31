Feature: Checkout item on cart
  Login
  Add one item to the cart
  Checkout
  Enter personal information and continue
  Finish


  Scenario: Add 1 item to the shopping cart and checkout
    Given I open chrome browser and navigate to SWAGLABS web application
    When I add item "Sauce Labs Backpack" to the cart and validate the cart
    Then I use "Ephraim" "Lebese" postal code "7145" to check out