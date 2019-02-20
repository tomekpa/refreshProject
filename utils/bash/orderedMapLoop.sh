#!/bin/bash

#curl --request --data '{"req_prop":"req_prop_val"}' POST https://tomekpa.free.beeceptor.com/hello

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
        echo -n "*"
#       echo ${endpointsOrder[$i]}
#        printf "%3s key" $i
#        printf "%10s value\n" ${endpointsOrder[$i]}
done

echo
echo "======================================="

#associative array
declare -A endpointsQueryMap
endpointsQueryMap[patient1]="curl https://tomekpa.free.beeceptor.com/hello"
endpointsQueryMap[bar2]="curl --request POST --data '{\"req_prop1\":\"req_prop_val1\"}' https://tomekpa.free.beeceptor.com/hello"
endpointsQueryMap[patient2]="curl --request POST --data '{\"req_prop2\":\"req_prop_val2\"}' https://tomekpa.free.beeceptor.com/hello"
endpointsQueryMap[bar1]="curl --request GET https://tomekpa.free.beeceptor.com/hello"

for i in "${!endpointsQueryMap[@]}"
    do
         echo -n "*"
#        printf "%8s key" $i
#        printf "%8s value\n" ${endpointsQueryMap[$i]}
done

echo
echo "======================================="

for i in "${!endpointsOrder[@]}"
    do
        commandName=${endpointsOrder[$i]}
#        echo ${endpointsOrder[$i]}
        echo ${endpointsQueryMap[$commandName]}
        commandValue=${endpointsQueryMap[$commandName]}
        eval $commandValue
        echo
done

echo
echo "======================================="