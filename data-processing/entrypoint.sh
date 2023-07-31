#!/bin/sh

mc alias set trafficminio http://traffic-bucket:9000 key12345 pwd12345

mc mb trafficbucket

echo "Coping files to bucket..."

mc cp /files/* trafficbucket/

echo "Listing bucket objects..."

mc ls trafficbucket


