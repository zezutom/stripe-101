# Stripe 101
## Introduction
This is a simple project to demonstrate how to use Stripe API.

## Requirements
- [Stripe account](#set-up-a-stripe-account)
- [Stripe CLI](#install-stripe-cli)
- [API keys](#create-api-keys)
- [Java 17+](#install-java)

## Set up a Stripe account
Create a Stripe account by following the instructions on the [Stripe registration](https://dashboard.stripe.com/register) page.

Note that you will need to provide a country or region where you business is incorporated.
If your country or region is not listed, please follow [my guide](https://www.tomaszezula.com/a-guide-to-opening-a-stripe-account-in-an-unsupported-country/).

## Install Stripe CLI
Install the Stripe CLI by following the instructions on the [Stripe CLI](https://stripe.com/docs/stripe-cli) page.

Once done, run the following command to login to your Stripe account:
```shell
stripe login
```

## Install Java
Go to the [Oracle Java download](https://www.oracle.com/java/technologies/downloads/) page and download the latest version of Java.
Alternatively, you can use [OpenJDK](https://openjdk.java.net/install/).
This project was tested with Java 17.

## Create API keys
Follow the instructions on the [API keys](https://stripe.com/docs/keys) page to create your API keys.

## Tutorial
1. [Payment links](./doc/payment-links.md)