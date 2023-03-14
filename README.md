# Asadal-RP
Some word based rpg.

### Launch DB
```bash
docker run \
--name asadal-postgres \
-p 5432:5432 \
-v ~/asadal/postgres/data:/var/lib/postgresql/data \
-e POSTGRES_USER=asadal \
-e POSTGRES_DB=asadal \
-e POSTGRES_PASSWORD=asadal \
--restart always \
-d \
postgres:15.2-alpine
```
