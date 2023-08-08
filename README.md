

## How to use

### using docker images
1. create your application.yaml, such as: /data/project/yaml/any-job-map/application.yml
2. run docker(us -v to mount your config to be used)
```shell
docker run --name any-job-mp -d -p 9031:9031 -v /data/project/yaml/any-job-map:/usr/local/config yuihtt/any-job-mp --spring.config.location=/usr/local/config/application.yml
```
### build your docker images
1. clone this project
2. override you application.yml
3. build
```shell
docker build -f Dockerfile --name any-job-mp:1.0 .
``` 
4. run
```shell
docker run -d -p 9031:9031 --name any-job-map any-job-mp:1.0
```

### java -jar
1. clone this project
2. mvn install
3. java -jar xxx.jar