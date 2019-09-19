Data analyzer in Java / Spring boot

**Example request**
```
$ curl -i -X POST -H "Content-Type:application/json" -d "{ \"url\": \"https://raw.githubusercontent.com/Flurrih/XMLAnalyzerSpring/master/testSample.xml\" }" 'http://localhost:8080/analyze/'

```
**Example response**
```
% Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   286    0   192  100    94    267    130 --:--:-- --:--:-- --:--:--   398HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 19 Sep 2019 14:55:42 GMT

{"analyseDate":"2019-09-19T14:55:41.913Z","details":{"firstPost":"2015-07-14T18:39:27.757+02:00","lastPost":"2015-07-14T22:28:24.197+02:00","totalPosts":9,"totalAcceptedPosts":1,"avgScore":2}}
```

**Docker**

1. Pull image from docker's hub (https://hub.docker.com/r/flurrih/data-analysis-spring-image)
```
docker pull flurrih/data-analysis-spring-image
```
2. Check if image was downloaded succesfully
```
$ docker images
REPOSITORY                           TAG                 IMAGE ID            CREATED             SIZE
flurrih/data-analysis-spring-image   1                   772cb41d8e86        29 minutes ago      122MB
```
3. Start docker image with spring boot server (using repository name followed by tag)
```
docker run -p 8080:8080 -t flurrih/data-analysis-spring-image:1
```

List all containers
```
docker ps -a
CONTAINER ID        IMAGE                                  COMMAND                  CREATED             STATUS              PORTS                    NAMES
072cd01ff82f        flurrih/data-analysis-spring-image:1   "java -jar /DataAnal…"   4 minutes ago       Up 4 minutes        0.0.0.0:8080->8080/tcp   condescending_mirzakhani
```

Stop container
```
docker stop 072cd01ff82f
```

Remove container
```
docker rm 072cd01ff82f
```
