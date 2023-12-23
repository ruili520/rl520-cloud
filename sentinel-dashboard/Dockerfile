FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER WenBo
#添加jar包和配置文件
COPY  ./target/sentinel-dashboard.jar /data0/sentinel-dashboard.jar
# copy sentinel jar
ENV JAVA_OPTS '-Dserver.port=8858'

RUN chmod -R +x /data0/sentinel-dashboard.jar

EXPOSE 8858

CMD java ${JAVA_OPTS} -jar /data0/sentinel-dashboard.jar

