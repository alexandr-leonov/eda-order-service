### OrderService in Event Driven Architecture (EDA) pet project.
Return to main: https://github.com/alexandr-leonov/eda-configuration

<br>
<br>

Service processing buy orders.

Service handle and send events by Kafka, has REST endpoints for operate data, store Order state to PostgreSQL.

Link to materials: 
 - https://stackoverflow.com/questions/53669151/java-11-application-as-lightweight-docker-image
 
For manual build image:
```
> docker build -t aleson/order-service:latest
> docker login
> docker push aleson/order-service:latest
```
