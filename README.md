**Data analyzer in Java / Spring boot**

**Example request**
```
$ curl -i -X POST -H "Content-Type:application/json" -d "{ \"url\": \"https://raw.githubusercontent.com/Flurrih/XMLAnalyzerSpring/master/testSample.xml\" }" 'http://localhost:8080/analyze/'

```
**Example response**
```
% Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   291    0   197  100    94   1397    666 --:--:-- --:--:-- --:--:--  2063HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 19 Sep 2019 21:11:50 GMT

{"analyseDate":"2019-09-19T21:11:50.403+00:00","details":{"firstPost":"2015-07-14T18:39:27.757+02:00","lastPost":"2015-07-14T22:28:24.197+02:00","totalPosts":9,"totalAcceptedPosts":1,"avgScore":2}}

```

**Docker**

1. Pull image from docker's hub (https://hub.docker.com/r/flurrih/data-analysis-spring-image)
```
docker pull flurrih/data-analysis-spring-image:2
```
2. Check if image was downloaded succesfully
```
$ docker images
REPOSITORY                           TAG                 IMAGE ID            CREATED             SIZE
flurrih/data-analysis-spring-image   1                   772cb41d8e86        29 minutes ago      122MB
```
3. Start docker image with spring boot server (using repository name followed by tag)
```
docker run -p 8080:8080 -t flurrih/data-analysis-spring-image:2
```

List all containers
```
docker ps -a
CONTAINER ID        IMAGE                                  COMMAND                  CREATED             STATUS              PORTS                    NAMES
a2c69af803ba        flurrih/data-analysis-spring-image:2   "java -jar /DataAnal…"   48 seconds ago      Up 47 seconds       0.0.0.0:8080->8080/tcp   sharp_visvesvaraya
```

Stop container
```
docker stop a2c69af803ba
```

Remove container
```
docker rm a2c69af803ba
```
