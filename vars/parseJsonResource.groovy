#!/usr/bin/groovy
import groovy.json.JsonSlurper;

@NonCPS
def call(jsonText) {
  final slurper = new JsonSlurper()
  return new HashMap<>(slurper.parseText(jsonText))
}