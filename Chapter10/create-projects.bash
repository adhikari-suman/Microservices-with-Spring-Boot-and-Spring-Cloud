#!/usr/bin/env bash

mkdir microservices
cd microservices

spring init \
--boot-version=3.4.2 \
--type=maven-project \
--java-version=17 \
--packaging=jar \
--name=product-service \
--package-name=np.com.suman_adhikari.microservices.core.product \
--groupId=np.com.suman-adhikari.microservices.core.product \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
product-service

spring init \
--boot-version=3.4.2 \
--type=maven-project \
--java-version=17 \
--packaging=jar \
--name=review-service \
--package-name=np.com.suman_adhikari.microservices.core.review \
--groupId=np.com.suman-adhikari.microservices.core.review \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
review-service

spring init \
--boot-version=3.4.2 \
--type=maven-project \
--java-version=17 \
--packaging=jar \
--name=recommendation-service \
--package-name=np.com.suman_adhikari.microservices.core.recommendation \
--groupId=np.com.suman-adhikari.microservices.core.recommendation \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
recommendation-service

spring init \
--boot-version=3.4.2 \
--type=maven-project \
--java-version=17 \
--packaging=jar \
--name=product-composite-service \
--package-name=np.com.suman_adhikari.microservices.composite.product \
--groupId=np.com.suman-adhikari.microservices.composite.product \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
product-composite-service

cd ..