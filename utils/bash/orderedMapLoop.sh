#!/bin/bash

echo "======================================="

#array
declare -a endpointsOrder=(
    "patient1"
    "patient2"
    "bar1"
    "bar2"
)

for i in "${!endpointsOrder[@]}"
    do
#       echo ${endpointsOrder[$i]}
        printf "%3s key" $i
        printf "%10s value\n" ${endpointsOrder[$i]}
done

echo "======================================="

#associative array
declare -A endpointsQueryMap
endpointsQueryMap[patient1]="curl_1"
endpointsQueryMap[bar2]="curl_4"
endpointsQueryMap[patient2]="curl_2"
endpointsQueryMap[bar1]="curl_3"

for i in "${!endpointsQueryMap[@]}"
    do
        printf "%8s key" $i
        printf "%8s value\n" ${endpointsQueryMap[$i]}
done

echo "======================================="

for i in "${!endpointsOrder[@]}"
    do
        command=${endpointsOrder[$i]}
        echo ${endpointsQueryMap[$command]}
done

echo "======================================="