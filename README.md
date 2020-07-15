

<p>
<img width="400" height="300" src="http://www.simpleorientedarchitecture.com/wp-content/uploads/2016/09/dddCover.jpg"/>
</p>

# DDD Code Exercise

# Background
Your mission is to create a simple banking system. Think about your personal bank account experience.
When in doubt, go for the simplest solution. 
You don’t need to create any kind of user interface or make these features available through any server.
The exercise evolves as a sequence of iterations. Try to complete each iteration before reading the next one.

## What to do
### Iteration 1: Add the deposit feature

You can follow this user story:

Feature: Deposit money into an account
    As a client of the bank
    I want to deposit money into my account
    In order to increase my balance

    Scenario: An existing client deposits money into his account
        Given an existing client with id “francisco” with 100 USD in his account
        When he deposits 10 USD into his account
        Then the balance of his account is 110 USD

### Iteration 2: Update the deposit feature

Currently, users can deposit negative amounts of money, which does not make sense. Add a new test case to fix this issue.

### Iteration 3: Add the withdrawal feature

You can follow this user story:

Feature: Withdraw money from an account
    As a client of the bank
    I want to withdraw money from my account
    In order to have cash

    Scenario: An existing client withdraws money from his account
        Given an existing client with id “francisco” with 100 USD in his account
        When he withdraws 10 USD from his account
        Then the new balance is 90 USD

### Iteration 4: Cover border cases for the withdrawal feature

Add a scenario in the withdrawal feature for the case when a withdrawal generates an overdraft. 
Withdrawal of amounts bigger than the current account balance must not be allowed.

### Iteration 5: Update the withdrawal feature

Check whether it is possible to withdraw a negative value. 
If it is possible, fix it. Add the corresponding test case to fix this issue.

### Iteration 6: Add the transfer feature

From now on, users will be able to transfer money from their account to the account of another user. 
Add this feature. First write a user story for it. 
Write the scenarios for the main case, but also write scenarios for border cases, such as:
can one transfer money one doesn't have? There may be others.

 Scenario1: An existing client transfer money from his account to another client
        Given an existing client1 with id “francisco” with 100 USD in his account
        Given an existing client2 with id “pedro” with 50 USD in his account
        When the client1 transfers 10 USD from his account to the client2
        Then the new balance the client1 is 90 USD and for the client2 is 60 USD

### Iteration 7: Expose business logic through a rest controller

Steps for using the REST API

 	Start the aplication with mvn spring-boot:run
 	Examples
	Create a new client francisco
		curl  -H "Content-Type: application/json" -X POST http://localhost:8080/client/francisco/100
		
	Create a new client pedro
		curl  -H "Content-Type: application/json" -X POST http://localhost:8080/client/pedro/50
		
	Deposit 10 to Francisco 
		curl  -H "Content-Type: application/json" -X POST http://localhost:8080/deposit/francisco/10
		
	Withdraw 10 from Francisco 
		curl  -H "Content-Type: application/json" -X POST http://localhost:8080/withdraw/francisco/10
		
	Transfer 10 from Francisco to Pedro
		curl  -H "Content-Type: application/json" -X POST http://localhost:8080/transfer/francisco/pedro/10
	
	For the full API see http://localhost:8080/swagger-ui.html#/simple-banking-controller


### Iteration 8: Add the account statement feature

Users have been using the system for a while now. 
But they are lacking a way to view all operations that have happened in their account.
Add the account statement feature. 
Remember: it is not necessary to print to the console or send the statement data anywhere.

You can follow this user story:

Feature: Obtain the account statement
As a client of the bank
I want to obtain the statement of my account
In order to review all operations that have occurred in it

Scenario: An existing client obtains the statement of his account
Given an existing client with id “francisco”
Given an existing client with id “pedro”
Given francisco first deposited 100 USD in his account
Given francisco then withdrew 20 USD from his account
Given francisco then transferred 50 USD from his account to pedro's account
Given francisco then deposited 10 USD in his account

When he obtains the statement of his account

Then the balance contains four lines, in the following order

1. deposit, 100 USD, balance: 100 USD
2. withdrawal, 20 USD, balance: 80 USD
3. transfer to pedro, 50 USD, balance: 30 USD
4. deposit, 10 USD, balance: 40 USD
