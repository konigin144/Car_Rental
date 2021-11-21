# Car_Rental
This is a simple project for car rental which contains API backend (<b>Java</b>, <b>Spring</b> and <b>Hibernate</b>) and database (<b>SQLite</b>).  

## Database
Database contains three tables: <b>Cars</b>, <b>Clients</b> and <b>Rents</b>. Database schema presented below:  
![db_diagram](https://user-images.githubusercontent.com/56699286/142764905-d4dc54ad-247b-4316-8cde-ab3d8202262e.PNG)

## Endpoints
* `GET /car` returns the list of all cars with status 200  
* `GET /car/{id}` returns the car specified by `id` with status 200 or status 400 if car is not found  
* `POST /car` adds the car with parameters `brand`, `model` and `available` given in body, returns 201 if created, 400 otherwise  
* `PUT /car/{id}` updates existing car specified by given `id` with parameters `brand`, `model` and `available` given in body, returns 200 if updated, 400 otherwise  
* `DELETE /car/{id}` deletes existing car specified by givem `id`, returns 200 if deleted, 400 otherwise  

* `GET /client` returns the list of all clients with status 200  
* `GET /client/{id}` returns the client specified by `id` with status 200 or status 400 if client is not found  
* `POST /client` adds the client with parameters `firstname` and `lastname` given in body, returns 201 if created, 400 otherwise  
* `PUT /client/{id}` updates existing client specified by given `id` with parameters `firstname` and `lastname` given in body, returns 200 if updated, 400 otherwise  
* `DELETE /client/{id}` deletes existing client specified by givem `id`, returns 200 if deleted, 400 otherwise  

* `GET /rent` returns the list of all rents with status 200  
* `GET /rent/{id}` returns the rent specified by `id` with status 200 or status 400 if rent is not found  
* `POST /rent` adds the rent with parameters `car_id` and `client_id` given in body, returns 201 if created, 400 otherwise  
* `PATCH /rent/{id}` sets the rent specified by `id` as returned: sets `returndate` to current time and sets car as available; returns 200 if updated, 400 otherwise
* `PUT /rent/{id}` updates existing rent specified by given `id` with parameters `car_id` and `client_id` given in body, returns 200 if updated, 400 otherwise  
* `DELETE /rent/{id}` deletes existing rent specified by givem `id`, returns 200 if deleted, 400 otherwise  

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/11539571-9788c0b5-fee4-437c-a76e-08d25ee6460c?action=collection%2Ffork&collection-url=entityId%3D11539571-9788c0b5-fee4-437c-a76e-08d25ee6460c%26entityType%3Dcollection%26workspaceId%3D2cca9ce2-a2e9-4383-a2f9-de59616e4bc6)
