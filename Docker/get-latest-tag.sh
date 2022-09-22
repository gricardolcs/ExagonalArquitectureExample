#!/bin/bash

# Receive string  as parameter.
# STRING_TAGS= "1.0.0build1 1.0.0build2 1.54.0build ..."
STRING_TAGS="$LIST_TAGS"
# Split string into array
readarray -d ' ' -t ARRAY_TAGS <<< $STRING_TAGS
# Find latest build in array like: ARRAY_TAGS: [1.0.0build1, 1.0.0build2, ...]
LATEST_BUILD=1
LATEST_TAG=""
for TAG in ${ARRAY_TAGS[@]}
do
#    BUILD_NUMBER=build54
   BUILD_NUMBER=$(echo "${TAG}" | grep -oP 'build\d+')
#    BUILD_NUMBER=54
   BUILD_NUMBER=${BUILD_NUMBER:5}
   REGEX='^[0-9]+$'
   if ! [[ $BUILD_NUMBER =~ $REGEX ]] ; then
   #    BUILD_NUMBER=.54.
        BUILD_NUMBER=$(echo "${TAG}" | grep -oP '\.\d+\.')
   #    BUILD_NUMBER=54.
        BUILD_NUMBER=${BUILD_NUMBER:1}
   #    BUILD_NUMBER=54
        BUILD_NUMBER=${BUILD_NUMBER::-1}
   fi
   if [ $BUILD_NUMBER -ge $LATEST_BUILD ]; then
        LATEST_BUILD=$BUILD_NUMBER
        LATEST_TAG=$TAG
   fi
done
echo $LATEST_TAG