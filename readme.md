# Accounting System

Accounting System is a Java 8 application that provides the core functionality of an accounting system and exposes a REST API

## Pre-requisites

Before trying to run the application, ensure that you have met the following requirements:

### Java 8

Open your terminal and execute the following command to verify your Java version.

```
#java -version
openjdk version "1.8.0_232"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_232-b09)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.232-b09, mixed mode)
```

### Apache Maven

Type the following command to verify if Apache Maven is working.
```
#mvn -v
Maven home: /Users/inerciobelton/Downloads/apache-maven-3.6.3
Java version: 1.8.0_102, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_102.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.12.6", arch: "x86_64", family: "mac"
```

### Jakarta EE 8 Application Server

Access the link [Jakarta EE Compatible Products](https://jakarta.ee/compatibility/) to see a full list of application servers that are compatible with the Jakarta EE 8 specification, including:

* [Wildfly 19.1.0](https://wildfly.org/downloads/)
* [Glassfish v5.1.0](https://projects.eclipse.org/projects/ee4j.glassfish/downloads)
* [OpenLiberty 19.0.0.9](https://openliberty.io/downloads/)


## Installation

To install the application locally, follow these steps:

Check out the source code to your computer:
```
git clone https://github.com/InercioBelton/accounting-system-ddd.git
```
Navigate to the project directory and run the following command to build a clean package locally:
```
mvn clean install

```

When the build is finished deploy the generated artifact to your favourite application server or use the DockerFile (at project root directory) to package the application using a Docker image.

Note: ensure that you have a postgresql datasource with the following JNDI name: `java:/AccountingSystem` configured in your application server.

## Usage

This application exposes a Rest API than can be accessed through the following endpoints:
 
* `POST /account`: [Create an account](/docs/createAccount.md)
* `POST /account/debit`: [Debit an account](/docs/debit.md)
* `POST /account/credit`: [Credit an account](/docs/credit.md)
* `POST /accountingJournal/entry`: [Post into the accounting Journal](/docs/postAccountingJournalEntry.md)
* `GET /accountingJournal/entries`: [List the entries of the Accounting Journal](/docs/listAccountingJournalEntries.md)
* `GET /accountingJournal/entry/{entryId}`: [Get the details of an Accounting Journal Entry](/docs/getAccountingJournalEntryById.md)
* `GET /accountingJournal/entry/byDescription/{description}`: [Find an Accounting Journal Entry by description](/docs/getAccountingJournalEntryByDescription.md)
* `GET /accountingJournal/entries/creationDate`: [List all Accounting Journal Entries that were created in a specific date](/docs/listAccountingJournalEntriesbyDate.md)
* `GET /accountingJournal/entries/betweenDates`: [List all Accounting Journal Entries that were created in a specific time interval](/docs/listAccountingJournalEntriesbyTimeInterval.md)
* `GET /accounts`: [List all accounts](/docs/listAccounts.md)
* `GET /accounts/asset`: [List all asset accounts](/docs/listAssetsAccounts.md)
* `GET /accounts/liability`: [List all liability accounts](/docs/listLiabilitiesAccounts.md)
* `GET /account/{accountNumber}/entries`: [List all posts made into an account](/docs/listAccountEntries.md)
* `GET /account/{accountNumber}/balance`: [Get the current balance of an account](/docs/getAccountBalance.md)
* `GET /accountingJournal/assetsValue`: [Get the assets value ](/docs/getAssetsValue.md)
* `GET /accountingJournal/liability`: [Get the liability value ](/docs/getLiabilitiesValue.md)
* `GET /accountingJournal/equityValue`: [Get the equity value ](/docs/getEquityValue.md)



