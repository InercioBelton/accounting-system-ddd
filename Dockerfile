FROM jboss/wildfly:19.1.0.Final

# add deployment archive.
ADD /target/accounting-system-ddd-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

# add an admin user.
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin#70365 --silent

# run as standalone mode.
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
