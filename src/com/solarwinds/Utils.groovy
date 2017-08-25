#!/usr/bin/groovy
package com.solarwinds

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

	command = "dig +noall +answer ${environment[evironmentName]} CNAME +short".toString()
	command.execute().text
}

@NonCPS
def isBlue(evironmentName){
	output = getCNAME(environment)
	echo output
	output.contains('blue')
}

@NonCPS
def isGreen(evironmentName){
	output = getCNAME(environment)
	echo output
	output.contains('green')
}

return this