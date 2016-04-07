#!/usr/bin/sh
mvn exec:java -Dorg.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=true -Dexec.mainClass="ui.twitter.app.TwitterApp"
