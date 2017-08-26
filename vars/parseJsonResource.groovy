#!/usr/bin/groovy
import groovy.json.JsonSlurper;

def call(resourceName){
	def resource = libraryResource resourceName
	def jsonSlurper = new JsonSlurper()
	jsonSlurper.parseText(resource)
}