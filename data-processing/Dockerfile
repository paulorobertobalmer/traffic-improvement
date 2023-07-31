FROM minio/mc

COPY ./files/ /files/

COPY entrypoint.sh /entrypoint.sh

COPY policy.json /policy.json

RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]