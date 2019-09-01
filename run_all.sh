#!/usr/bin/env bash

cd eureka-server/ & gradle bootrun
cd auth-manager/ & gradle bootrun
cd api-gateway/ & gradle bootrun
cd book-manager/ & gradle bootrun
