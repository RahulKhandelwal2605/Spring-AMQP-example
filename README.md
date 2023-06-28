# Project 
## Spring-AMQP-example

# Description
## Project for learning basic of RabbitMQ and Spring connection using Advanced Message Queuing Protocol (AMQP)

# Pre-requiste
## 1) Java 17
## 2) IDE (Eclipse or IntelLiJ)
## 3) Docker Dashboard

# How to Run
## run below Docker command to download RabbitMQ docker image
## docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management

## Then run the application according to example:
### For Simple Flow : Can Run simple/SpringAmqpExampleApplication
### For broadcast flow (Fanout and topic broadcast concept) : broadcast/BroadCastMessageApp
