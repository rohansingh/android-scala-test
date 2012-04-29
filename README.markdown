Android Scala Test
==================

This is a simple proof-of-concept for an Android project using Scala and Maven. It is only slightly
more complicated than "Hello World". I am just using it as a way to remember the specific Maven and
ProGuard configuration that's needed to build a Scala project for Android.

Currently, this project targets Scala 2.9.2.

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

   After you have the Android SDK installed, set the `ANDROID\_HOME`
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
   happens:

     1. Make sure you have the $ANDROID_HOME location properly set.

     2. Open the Android SDK tool and make sure you have the necessary
        SDK's and API versions downloaded.

     3. If the deployer complains that a file is missing, look closely at
        the path. You may have the same file but in a path where some of
        the hyphens are instead underscores. If this is the case, just link
        the path that the deployer is searching for to the correct one.

3. Install libraries to local repository:

        $ chmod +x install-libs.sh
        $ ./install-libs.sh

4. If you are using OS X, also run:

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
