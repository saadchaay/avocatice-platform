> /opt/Application/sirhus/logs/sissm-registry.log
nohup java -Xms256m -Xmx1024m -jar -DSpring.profiles.active=prod -Dserver.port=8761 /opt/Application/sirhus/api/sissm-registry-prod-1.0.0.0-SNAPSHOT.jar 2>&1 &