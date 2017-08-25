
//this requires staticMethod org.codehaus.groovy.runtime.DefaultGroovyMethods execute java.lang.String
def getCNAME(environmentName){
	def branchMapToEnvironment = [develop: 'test', staging: 'staging', master: ['emea', 'us', 'apac']]

	def environment = [staging: 'arsn-stg-api.tst.system-monitor.com',
					   testing: 'arsn-testing-api.tst.system-monitor.com',
					   emea: 'checks-api.emea.system-monitor.com',
					   us: 'checks-api.us.system-monitor.com',
					   apac: 'checks-api.apac.system-monitor.com']

	command = "dig +noall +answer ${environment[environmentName]} CNAME +short".toString()
	command.execute().text
}

def call() {
	def environmentName = env.Branch_Name

	if(environmentName == "develop"){
		environmentName = 'testing'
	}

	isBlue(environmentName)
}

def isBlue(environmentName){
	getCNAME(environmentName)
}

@NonCPS
def isGreen(environmentName){
	getCNAME(environmentName)
}