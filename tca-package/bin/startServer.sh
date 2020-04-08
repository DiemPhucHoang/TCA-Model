#!/bin/bash

KARAF_ENV_FILE=bin/setenv
DB_STARTUP_RETRY_ATTEMPTS=180
DATASOURCE_CFG=opt/karaf/etc/org.ops4j.datasource-tca.cfg

checkAndExport(){
    if [ -z ${!1+x} ]; then
        export $1=$2
        echo "export $1=$2" >> $KARAF_ENV_FILE
    fi
}

checkAndUpdateEnvironment() {
  checkAndExport DB_URL jdbc:mariadb://db:3306/tca
  checkAndExport DB_HOST 127.0.0.1
  checkAndExport DB_USER root
  checkAndExport DB_PASS mysql
  checkAndExport DB_NAME tca
}

function configDataSource() {
  echo "====================================================================="
  echo "Creating datasource cfg file ..."
  fileContent="osgi.jdbc.driver.class=org.mariadb.jdbc.Driver\nosgi.jdbc.driver.name=mariadb\npool=dbcp2\nxa=true\ndatabaseName=tca\ndataSourceName=tca\norg.apache.karaf.features.configKey=org.ops4j.datasource-tca\nuser=$DB_USER\npassword=$DB_PASS\nurl=$DB_URL?createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
  echo -e $fileContent > $DATASOURCE_CFG
  echo "DB properties configured with $fileContent for user: $DB_USER"
}

function waitForDBStartup(){
  echo "====================================================================="
  echo "Checking DB status ..."
  for (( i=1; i <= $DB_STARTUP_RETRY_ATTEMPTS; i++));
    do
       MYSQL_STATUS=$(java -jar opt/lib/create-database.jar "ping" "$DB_URL" "$DB_USER" "$DB_PASS")
       if [ "$MYSQL_STATUS" == "SUCCESS" ]; then
           echo "DB is started..."
           echo "DB database is already available.. skip creation of TCA database..."
           break
       elif [ "$MYSQL_STATUS" == "UNKNOWN_DATABASE" ]; then
           echo "DB is started..."
           echo "TCA database not available..."
           #Create a TCA database when MariaDb started
           break
       else
          echo $MYSQL_STATUS
          echo "Wait for DB startup... $(($DB_STARTUP_RETRY_ATTEMPTS - $i)) attempts remaining"
          if [ $i -eq $DB_STARTUP_RETRY_ATTEMPTS ]; then
             echo "DB is not started, give up!!!"
             exit 1
          fi
          sleep 1
       fi
  done
}

function waitForDBStartupX(){
  echo "====================================================================="
  echo "Checking DB status ... (dummy)"
  for ((i=1; i <= 6; i++));
  do
    echo "Checking DB status time $i"
    sleep 5
  done
}

function startKaraf() {
  echo "====================================================================="
  echo "Starting TCA Server ..."
  if [ "$DEBUG" = "true" ]
    then
      echo "DEBUG enabled via port 5005"
      opt/karaf/bin/karaf clean debug
    else
      opt/karaf/bin/karaf clean
    fi
}

#Execute
checkAndUpdateEnvironment
configDataSource
waitForDBStartupX
startKaraf