# miniprojekti

## settings for local db

- User: miniprojekti
- Password: secret
- Database name: miniprojekti
- PortNumber: 5432

## building the frontend

    npm install -g gulp bower coffee-script
    cd web
    npm install
    bower install
    gulp
    cp -R build/* ../src/main/webapp

## running

    mvn jetty:run
