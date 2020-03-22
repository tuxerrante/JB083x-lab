## J2EE WITH JBOSS EAP PLAYGROUND

#### CONFIG
1. Download JBOSS 7 or install it through Eclipse New Server
2. config the settings.xml: put it in the ~/.m2 direcotry or use the local one with `mvn -s settings.xml`

Run JBoss in bash to be independent from Eclipse restarts or crashes:  

```
$ sh standalone.sh --server-config=standalone-full.xml -Djboss.server.base.dir=/home/alex/jboss-eap-7.0/standalone  "-Dprogram.name=JBossTools: Red Hat JBoss Enterprise Application Platform 7.0 at localhost" -Dorg.jboss.resolver.warning=true -Djava.net.preferIPv4Stack=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true "-Dorg.jboss.boot.log.file=/home/alex/jboss-eap-7.0/standalone/log/boot.log" "-Dlogging.configuration=file:/home/alex/jboss-eap-7.0/standalone/configuration/logging.properties" "-Djboss.home.dir=/home/alex/jboss-eap-7.0" -Dorg.jboss.logmanager.nocolor=true -Djboss.bind.address.management=localhost 
```

Logs will be also in ` ~/jboss-eap-7.0/standalone/log/server.log` 


#### FIRST DEPLOY:
- run the server
- hello-web$ mvn clean package wildfly:deploy -s ../settings.xml 
- create admin user: `sh jboss-eap-7.0/bin/add-user.sh`
- verify your deploy from `http://127.0.0.1:8080/hello-web/index.jsf` and `http://localhost:9990/console/App.html#standalone-deployments/deployments-details`

