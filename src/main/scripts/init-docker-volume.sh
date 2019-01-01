#!/usr/bin/env bash
mkdir -p ~/volumes/cherry/data/nats/nats-0
mkdir -p ~/volumes/cherry/data/nats/nats-1
mkdir -p ~/volumes/cherry/data/nats/nats-2
mkdir -p ~/volumes/cherry/data/kafka/kafka-0
mkdir -p ~/volumes/cherry/data/kafka/kafka-1
mkdir -p ~/volumes/cherry/data/kafka/kafka-2
mkdir -p ~/volumes/cherry/data/pulsar
mkdir -p ~/volumes/cherry/configs

mkdir -p ~/volumes/cherry/data/redis/master-data
mkdir -p ~/volumes/cherry/data/redis/node-1-data
mkdir -p ~/volumes/cherry/data/redis/node-2-data

mkdir -p ~/volumes/cherry/data/nginx/log
mkdir -p ~/volumes/cherry/data/nginx/www

cp -r -f ../configs ~/volumes/cherry/

chmod 600 ~/volumes/cherry/configs/nginx/cert/*.crt
chmod 600 ~/volumes/cherry/configs/nginx/cert/*.key

chmod 600 ~/volumes/cherry/configs/roach/*.crt
chmod 600 ~/volumes/cherry/configs/roach/*.key