#!/bin/sh

yarn build

scp -r admin_web root@39.107.94.155:/www/apache-tomcat-9.0.26/webapps/ROOT