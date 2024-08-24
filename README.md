# PaymentDemo
Its a payment demo project using CC mocker
The project uses Kafka for Producer and Consumer Process
Producer application Payment produces a Kafka topic which is consumed by Payment Core application as consumer.
Authorization using JWT token is implemented.
DB used here is Postgre
Tables used --- card_details, oauth_access_token, oauth_client, transaction_details, user_auth

To run the project
1. mvn clean install for both projects
2. Docker-compose up
3. Call the API -- http://localhost:9132/api/payment/create-user using {
  "username": "amit.lamba@gmail.com",
  "password": "test@1234"
}
4. It will create a user and assign a merchant ID
5. Use the user to retrieve a token API to be used --- http://localhost:9132/api/payment/v1/retrieve-token using {
  "loginId": "amit.lamba@gmail.com",
  "password": "test@1234"
}
6. Cardnumber and amount can be inserted before this API call using a insert query
INSERT INTO card_details
(id, available_card_limit, card_cvv, card_expiry, card_holder_name, card_limit, credit_card_number, created_at, merchant_id)
VALUES(2, 1000.0, '033', '2010-10-24 00:00:00', 'amit lamba', 1000.0, '4325432543254325', '2024-08-23 13:41:09', 'MC73716');
7. Auth details also be needed to inserted before like
INSERT INTO oauth_client
(client_id, access_token_validity, authorized_grant_types, client_key, client_secret, created_date, refresh_token_validity, scopes, update_timestamp)
VALUES(1, 3600, 'ADMIN', 'client', 'password', '2024-08-05 16:57:39', 4000, 'ADMIN', '2024-08-05 16:57:39');
8. Once you got the accesstoken use the same in header and call the http://localhost:9132/api/payment/transaction/initiate-transaction using {
  "cardNumber": "4545454545454545",
  "amount": 200,
  "merchantId": "MC47256"
}
9. Fetch Transaction history can be checked using API --- http://localhost:9132/api/payment/transaction/fetch-transaction using MC47256
