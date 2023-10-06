        FROM bellsoft/liberica-openjdk-alpine:20.0.2

        #install curl jq
        RUN apk add curl jq

        #WORKSPACE
        WORKDIR /home/selenium-docker

        #add the required files
        ADD target/docker-resources .
        ADD runner.sh  runner.sh

        # start the runner.sh
        ENTRYPOINT sh runner.sh
