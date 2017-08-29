//this requires staticMethod org.codehaus.groovy.runtime.DefaultGroovyMethods execute java.lang.String
def call(currentEnvironment) {
	if (currentEnvironment == 'blue') {
		'green'
	} else if (currentEnvironment == 'green') {
		'blue'
	} else {
		'unknown'
	}
}