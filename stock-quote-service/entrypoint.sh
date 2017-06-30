#!/usr/bin/env bash

CMD=$1

echo "Command :" $CMD

JAVA_OPTS="$JAVA_OPTS  -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses -Duser.timezone=America/Los_Angeles -Dspring.profiles.active=default -Dhttps.protocols=TLSv1.1,TLSv1.2 -Djava.security.egd=file:/dev/./urandom"
echo $JAVA_OPTS

case "$CMD" in
    "start")
        echo "Starting SpringBoot application"
        exec java $JAVA_OPTS -jar /app.jar
    ;;

    * )
    # custom command
    exec $CMD ${@:2}
    ;;
esac