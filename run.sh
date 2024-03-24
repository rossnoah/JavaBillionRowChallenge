#!/bin/sh

java -Xmx4G -XX:+EnableDynamicAgentLoading --class-path target/billionrowchallenge-1.0-SNAPSHOT.jar dev.noah.Main
