//this requires staticMethod org.codehaus.groovy.runtime.DefaultGroovyMethods execute java.lang.String
@NonCPS
def call(URLs, environmentName) {
	def blueGreenMap[:]
	if(URLs.containsKey(environmentName))
	{
		command = "dig +noall +answer ${URLs[environmentName]} CNAME +short".toString()
		output = command.execute().text

		if (output.contains('blue')) {
			map['current'] = 'blue'
			map['build'] = 'green'
		} else if (output.contains('green')) {
			map['current'] = 'green'
			map['build'] = 'blue'
		} else {
			map['current'] = 'unknown'
			map['build'] = 'unknown'
		}
	} else {
		map['current'] = 'unknown'
		map['build'] = 'unknown'
	}
}