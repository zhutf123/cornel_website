#!/bin/sh

yarn build

scp -r admin_web root@120.77.214.74:/www/apache-tomcat-9.0.26/webapps/ROOT