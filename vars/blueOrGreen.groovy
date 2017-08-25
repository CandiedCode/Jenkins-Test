import groovy.json.JsonSlurper;
//this requires staticMethod org.codehaus.groovy.runtime.DefaultGroovyMethods execute java.lang.String

def call(URLs, environmentName) {
	def jsonSlurper = new JsonSlurper()
	def urlResources = jsonSlurper.parseText(URLs)

	command = "dig +noall +answer ${urlResources[environmentName]} CNAME +short".toString()
	output = command.execute().text

	if (output.contains('blue')) {
		'blue'
	} else if (output.contains('green')) {
		'green'
	} else {
		'unknown'
	}
}