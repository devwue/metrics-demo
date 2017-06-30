FROM openjdk:alpine

VOLUME /tmp

COPY entrypoint.sh /

ADD target/*.jar app.jar

RUN apk add --update bash && rm -rf /var/cache/apk/* \
    && bash -c 'touch /app.jar' \
    && chmod 700 /entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]

CMD ["start"]