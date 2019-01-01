docker exec roach-2 sh -c 'export HOSTPARAMS="--host roach-2 --certs-dir=certs" &&
export SQL="/cockroach/cockroach.sh sql $HOSTPARAMS" &&
$SQL -e "CREATE DATABASE cherry;" &&
$SQL -d cherry -e "CREATE USER IF NOT EXISTS cherry WITH PASSWORD 'cherry';" &&
$SQL -e "GRANT ALL ON DATABASE cherry TO cherry;"'