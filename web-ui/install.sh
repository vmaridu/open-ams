#!/bin/bash

# install bower globally
npm install -g bower

# install npm comoinets locally
npm install

# remove old bower components
rm -rf bower_components

# install bower app dependecies
bower install
