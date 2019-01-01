#!/bin/bash
echo Wait for servers to be up
sleep 10

HOSTPARAMS="--host roach-2 --certs-dir=certs"
SQL="/cockroach/cockroach.sh sql $HOSTPARAMS"

$SQL -e "CREATE DATABASE cherry;"
$SQL -d cherry -e "CREATE USER IF NOT EXISTS cherry WITH PASSWORD 'cherry';"
$SQL -e "GRANT ALL ON DATABASE cherry TO cherry;"
