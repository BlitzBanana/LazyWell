#!/bin/sh
cd $CI_HOME/client_android
sbt ++$TRAVIS_SCALA_VERSION package