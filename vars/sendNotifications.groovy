#!/usr/bin/env groovy

/**
 * Send notifications based on build status string
 */
def call(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def color = '#FF0000'
  def message = "${buildStatus}: Job: '${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Last Commit: ${env.GIT_AUTHOR_NAME}'"
  // def message = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})"

  // Override default values based on build status
  switch (buildStatus) {
    case 'STARTED':
        color = '#FFFF00'
        break
    case 'SUCCESSFUL':
    case 'SUCCESS':
        color = '#00FF00'
        break
    case 'UNSTABLE':
    case 'FAILURE':
        color = '#FFFF00'
        break
    default:
        color = '#FF0000'
        break
  }  
  
  // Send notifications
  slackSend (color: color, message: message)
}
