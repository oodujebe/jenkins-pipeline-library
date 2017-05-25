#!/usr/bin/env groovy

/**
 * Send notifications based on build status string
 */
def call(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def color = '#FF0000'
  def message = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})"

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = '#FFFF00'
  } else if (buildStatus == 'SUCCESSFUL') {
    color = '#00FF00'
  } else if (buildStatus == 'UNSTABLE') {
    color = '#FFFF00'
  } else {
    color = '#FF0000'
  }

  // Send notifications
  slackSend (color: color, message: message)
}