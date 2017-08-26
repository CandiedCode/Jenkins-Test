import groovy.json.JsonSlurper;
//this requires staticMethod org.codehaus.groovy.runtime.DefaultGroovyMethods execute java.lang.String

def call(URLs, environmentName) {
	if(URLs.containsKey(environmentName))
	{
		command = "dig +noall +answer ${URLs[environmentName]} CNAME +short".toString()
		output = command.execute().text

		if (output.contains('blue')) {
			'blue'
		} else if (output.contains('green')) {
			'green'
		} else {
			'unknown'
		}
	} else {
		'unknown'
	}
}