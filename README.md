# sonarqube-maven-plugin 

TravisCI status: 
[![Build Status](https://travis-ci.org/sansp00/sonarqube-maven-plugin.svg?branch=master)](https://travis-ci.org/sansp00/sonarqube-maven-plugin)

CodeCov status:
[![codecov](https://codecov.io/gh/sansp00/sonarqube-maven-plugin/branch/master/graph/badge.svg?token=VaGVMn4yFj)](https://codecov.io/gh/sansp00/sonarqube-maven-plugin) 

Dependencies managed by DependaBot.

SonarQube client maven plugin

Maven plugin that currently provides the following interaction with SonarQube

*	Assign a quality profile to a project  (assign-profile)
*	Assign a quality profile to multiple projects (batch-assign-profile)
*   Delete a project (delete)
*   Delete multiple projects (batch-delete)
*   Export in JSON SonarQube issues from multiple projects (batch-issues-export)
*   Console report SonarQube issues from a project (issues)
*   Export in JSON SonarQube measures from multiple projects (batch-measures-export)
*   Console report SonarQube measures from a project (measures)
*   Update the visibility of a project (update-visibility)
*   Update the visibility of of multiple projects (batch-update-visibility)
*	Provision a project (provision)
*   Search for a project (search)
*   Search for current user projects (search-mine)

## Invocation
mvn com.github.sansp00.maven:sonarqube-maven-plugin:[VERSION]:[GOAL]


## Common parameters
Common parameters applicable to all goals 
Parameters:
*   username : SonarQube user name or token
*   password : SonarQube user password
*   url (required) : SonarQube Rest URL

## Common project parameters
Common parameters applicable to all goals relative to a project 
Parameters:
*   branch (default: master) : project source repository branch name
*   suffixBranchToKey (default: true) : append branche name to project key

## Profile assignment

###   assign-profile
The goal assigns the current project to a quality profile
Parameters: 

*   qualityProfile (required) : quality profile name
*   language (default: java) : project language

###   batch-assign-profile
The goal assigns projects to a quality profile
Parameters: 

*   qualityProfile (required) : quality profile name
*   language (default: java) : project language
*   keys (list) : project keys
*	keysFile (file) : JSON file containing an array of project keys

## Project deletion

###   delete
The goal deletes the current project from SonarQube.

###   batch-delete
The goal deletes projects from SonarQube.
Parameters: 

*   keys (list) : project keys
*	keysFile (file) : JSON file containing an array of project keys

## Project issues

###	 issues
The goal displays the issues linked to the current project
Parameters: 

*   resolved (default: false) : display resolved issues
*   severities (list) : severities to display (Blocker, Critical, Major, Minor), all are displayed of not specified
*   types (list) : types to display (Bug, Vulnerability, Code smell), all are displayed of not specified

###	 batch-issues-export
The goal export the issues linked to projects in a JSON format
Parameters: 

*   resolved (default: false) : display resolved issues
*   severities (list) : severities to display (Blocker, Critical, Major, Minor), all are displayed of not specified
*   types (list) : types to display (Bug, Vulnerability, Code smell), all are displayed of not specified
*   keys (list) : project keys
*	keysFile (file) : JSON file containing an array of project keys
*   jsonOutputFile (default: issues.json) : output filename 
*   encoding (default: project.reporting.outputEncoding) : output encoding

## Project measures

###	 measures
The goal displays the issues linked to the current project
Parameters: 

*   resolved (default: false) : display resolved issues
*   severities (list) : severities to display (Blocker, Critical, Major, Minor), all are displayed of not specified
*   types (list) : types to display (Bug, Vulnerability, Code smell), all are displayed of not specified

###	 batch-measures-export
The goal export the issues linked to projects in a JSON format
Parameters: 

*   fromDate : date range start 
*   toDate :  date range end
*   keys (list) : project keys
*	keysFile (file) : JSON file containing an array of project keys
*   jsonOutputFile (default: issues.json) : output filename 
*   encoding (default: project.reporting.outputEncoding) : output encoding

## Project visibility

###	 update-visibility
The goal update the current project visibility
Parameters: 

*   visibility (required) : project visibility (private, public)

###	 batch-update-visibility
The goal update projects visibility
Parameters: 

*   visibility (required) : project visibility (private, public)
*   keys (list) : project keys
*	keysFile (file) : JSON file containing an array of project keys

## Project provisioning

###	 provision
The goal provision the current project within SonarQube
Parameters: 

*   name (default: project.name ) : project name
*   visibility (default: PRIVATE) : project visibility (private, public)
*   language (default: java) : project language
*   qualityProfile : quality profile name

## Project search

### search
The goal displays project search results
Parameters: 

*   keyword :  keyword
*   groupIdFilter : project coordinates group identifier filter
*   artifactIdFilter : project coordinates artifact identifier filter

### search-mine
The goal displays current user project search results
Parameters: 

*   keyword :  keyword
*   groupIdFilter : project coordinates group identifier filter
*   artifactIdFilter : project coordinates artifact identifier filter

