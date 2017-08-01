# springboot-unit-test
A Spring Boot Unit Test Sample 

## Adding JGitflow credential fix/workaround.
Fixed jgit api 401 authorization issue

Known issue:  This gradle plugin is not deleting remote release branch once released new version, manually delete release-* branch is required before you do next release. :-(