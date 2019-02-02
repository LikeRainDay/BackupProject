# start consul container
docker run -d  -p 8300:8300 -p 8500:8500 -p 8600:8600 -p 8301:8301 -p 8302:8302  --name=dev-consul consul