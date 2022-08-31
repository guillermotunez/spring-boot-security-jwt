#!/bin/bash

cd $(dirname "$0")
docker-compose -p users down -v