#!/bin/bash

cd $(dirname "$0")
export HOST_APPLICATION="host.docker.internal"
export HOST_DOCKER_ENGINE="localhost"
#Local resources (camunda flows)
#export RESOURCES="../../src/main/resources"

docker-compose -p users up -d --build --remove-orphans
