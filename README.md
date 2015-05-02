# miniprojekti [![Build Status](https://travis-ci.org/superryhma/miniprojekti.svg?branch=master)](https://travis-ci.org/superryhma/miniprojekti)

[Trello](https://trello.com/b/ri1n2SHn)

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

## Report

[Google docs link](https://docs.google.com/document/d/1mk23o2GWyNYSjm_RASBTUcr6gZ0NKEm2_QQ5-OaUhGc/edit?usp=sharing)
