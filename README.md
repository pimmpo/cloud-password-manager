##Build docker image

mvn spring-boot:build-image -Dspring-boot.build-image.imageName=application

##Run application

docker-compose up -d --build
