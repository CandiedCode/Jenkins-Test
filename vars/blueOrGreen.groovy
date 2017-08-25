branchMapToEnvironment = [develop: 'test', staging: 'staging', master: ['emea', 'us', 'apac']]


environment = [staging: 'arsn-stg-api.tst.system-monitor.com',
		   testing: 'arsn-testing-api.tst.system-monitor.com',
		   emea: 'checks-api.emea.system-monitor.com',
		   us: 'checks-api.us.system-monitor.com',
		   apac: 'checks-api.apac.system-monitor.com']

def getCNAME(branchName){
	if(environmentName == "develop"){
		environmentName = 'testing'
	}

	command = "dig +noall +answer ${environment[environmentName]} CNAME +short".toString()
	command.execute().text
}

def call(body) {
	def environmentName = env.Branch_Name

	if(environmentName == "develop"){
		environmentName = 'testing'
	}

	isBlue(environmentName)
}

def isBlue(environmentName){
	output = getCNAME(environmentName)
	echo output
	output.contains('blue')
}

@NonCPS
def isGreen(environmentName){
	output = getCNAME(environmentName)
	echo output
	output.contains('green')
}