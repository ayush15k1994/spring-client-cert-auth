# spring-client-cert-auth
Demo Spring Project for Client Certificate Authentication

## Prerequisites
- Java 1.8 or higher
- Maven (for building the project)

## Installation
- Run `mvn clean install` in project's root directory
- The `.jar` file is generated in `target` directory in project's root folder.
- Run `java -jar spring-x509-client-certificate-authentication.jar` to run the application.
- The server should be accessible at __https://localhost:8443__

## Certificate Installation
- Add your root CA public key to *truststore.jks* and your server certificate (both private and public key) to *keystore.jks*
- Place both *.jks* files in the same directory as the jar file to run the application.
  * Adding a cetificate to truststore:\
    `keytool -import -trustcacerts -noprompt -alias ca -file rootCA.crt -keystore truststore.jks`
  * Adding a certificate to keystore:\
    `keytool -importkeystore -srckeystore localhost.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS`