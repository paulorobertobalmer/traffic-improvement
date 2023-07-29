#!/bin/sh

mc alias set trafficminio http://traffic-bucket:9000 your_access_key your_secret_key

echo "Coping files to bucket..."

mc cp /files/*.csv trafficminio/traffic-bucket/to-process/


