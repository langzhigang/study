FROM tomcat8-jre8
VOLUME /usr/local/tomcat/logs
RUN rm -rf /usr/local/tomcat/webapps/*
ADD conf /usr/local/tomcat/conf
ADD target/study.war /usr/local/tomcat/webapps
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
CMD ["catalina.sh", "run"]