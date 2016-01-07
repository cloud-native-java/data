#!/usr/bin/env bash

set -e

source $BUILD_DIRECTORY/utils/cf-common.sh

# Create an AWS for docker compost
sh $BUILD_DIRECTORY/docker-aws.sh create

# Source the environment variables for the integration testing containers
source $BUILD_DIRECTORY/utils/docker-aws.sh
