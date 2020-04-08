build code
    mvn clean install
    
build docker images
    cd tca-package
    mvn docker:build
    
start system
    cd tca-package
    docker-compose -f tca.yml up -d

======================================================================
feature:repo-add mvn:org.example.tca/tca-features/1.0-SNAPSHOT/xml
feature:install tca-api
feature:install tca-service
feature:install tca-rest

