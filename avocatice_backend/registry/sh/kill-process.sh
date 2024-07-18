#!/usr/bin/sh
jarFile=$(echo $1*.jar | tr -d '[]'| tr -d '[:space:]')
cp /opt/Application/sirhus/api/$jarFile    /opt/Application/sirhus/api/archive
for pid in  $(ps -ef | grep $1 | awk '$1!="root" {print $1,$2}' | awk '{print $2}')
do
  kill -9 $pid
done