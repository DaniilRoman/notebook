FROM postgres:11.5-alpine
COPY init.sql /docker-entrypoint-initdb.d/init.sql
ENV PGDATA=/data
EXPOSE 5432
ENTRYPOINT ["/docker-entrypoint.sh"]

