Scala on Android
==================

This is a simple proof-of-concept for an Android project using [Scala](http://www.scala-lang.org/)
and [Maven](http://code.google.com/p/maven-android-plugin/). It is only slightly
more complicated than "Hello World".

Currently, this project targets Scala 2.9.2. You can use it in two ways:

1. Just clone and build the project (setup and build instructions are below) to see a working
   proof-of-concept of using Scala to create Android apps.

2. Fork the project and use it as a base for an Android app that you would like to write in Scala.

Why?
----
There's a few reasons for this project:

* Building Scala for Android requires specific [ProGuard](http://developer.android.com/guide/developing/tools/proguard.html)
  [configuration](http://proguard.sourceforge.net/index.html#manual/usage.html) in order to bring the built
  app down to a reasonable size (or even a size that works with Dalvik), while not accidentally
  optimizing away necessary dependencies.

* Using Maven provides a repeatable and portable build process, but the pom.xml needed to use
  the [android-maven-plugin](http://code.google.com/p/maven-android-plugin/) with Scala is not
  entirely obvious.

* Other projects like this exist, but they are targeting outdated versions of android-maven-plugin,
  older versions of Scala, missing features like release-mode APK signing, or simply don't work for
  other reasons.

The "why" of using Scala instead of Java is beyond the scope of this document, though.

Roadmap
-------
* Package this project into a Maven archetype. This would make it easy to start brand new Scala on
  Android projects by just running `mvn archetype:generate`.

* Add a skeleton of some sort for adding unit tests.

Prerequisites
-------------
* Maven 3 (`brew install maven` or `apt-get install maven3`)
* Android SDK

Setup
-----
1. If you don't have the Android SDK, it is available at:
   [http://developer.android.com/sdk/](http://developer.android.com/sdk/)

   Note that after you download the SDK starter package from the link
   above, you must use the installer to install the version of the
   Android SDK targeted by the app. As of the time of this writing,
   that is Android 2.2.

   After you have the Android SDK installed, set the `ANDROID_HOME`
   environment variable. For example, on my machine:

        $ export ANDROID_HOME=/opt/google/android-sdk

   The location of the SDK is likely to be different on your machine.

2. Deploy the Android SDK to your local Maven repository:
   
        $ cd /tmp
        $ export ANDROID_HOME=<your-android-sdk-path>
        $ git clone https://github.com/mosabua/maven-android-sdk-deployer.git
        $ cd maven-android-sdk-deployer
        $ mvn install -P 2.2

   The `-P 2.2` in the final command specifies the version of the
   Android SDK to deploy. As of the time of this writing, the app
   targets the 2.2 SDK.

   The deployer may fail due to being unable to find certain files. If this
   happens: (a) make sure you have the `$ANDROID_HOME` location properly set
   and (b) open the Android SDK tool and make sure you have the necessary
   SDK's and API versions downloaded.
 
   If the deployer complains that a file is missing, look closely at
   the path. You may have the same file but in a path where some of
   the hyphens are instead underscores. If this is the case, just link
   the path that the deployer is searching for to the correct one.
 
3. If you are using OS X, also run:

        $ ./link-jars-for-osx.sh

Build
-----
You can build using Maven:

    $ mvn clean install

This will compile the project and generate an APK. The generated APK is
signed with the Android debug certificate. To generate a zip-aligned APK
that is signed with an actual certificate, use:

    $ mvn clean install -Prelease

The configuration for which certificate to use is in pom.xml.

Run
---
Setup and start an Android virtual device, or connect a dev device.
You can setup and run virtual device using:

    $ <your-android-sdk-path>/bin/android avd

Deploy to the virtual/dev device:

    $ mvn android:deploy

Using an IDE
------------
You can use Maven to generate project files for Eclipse or IDEA:

    $ mvn eclipse:eclipse
    $ mvn idea:idea

Once the project files are generated, your IDE should be able to open
the project. Depending on your IDE's configuration, it may not be able
to build the project. You can always build using the Maven command line
as detailed in this readme.

Further Reading
---------------
- [Android Maven Plugin](http://code.google.com/p/maven-android-plugin/)
