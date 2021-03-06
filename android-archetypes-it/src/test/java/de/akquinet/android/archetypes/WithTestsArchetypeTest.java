/*
 * Copyright 2010 akquinet
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.akquinet.android.archetypes;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.junit.Before;
import org.junit.Test;


public class WithTestsArchetypeTest {

    @Before
    public void setUp() {
        if (System.getProperty("archetype.version") == null) {
            System.setProperty("archetype.version", "1.0.6-SNAPSHOT");
        }
    }

    /**
     * Checks the with-test archetype with no parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestDefault() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-default");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/application");
        verifier.assertFilePresent("android-test/application-it");
        verifier.assertFilePresent("android-test/application/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/application-it/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/application/pom.xml");
        verifier.assertFilePresent("android-test/application-it/pom.xml");

        verifier.assertFilePresent("android-test/application/res/values/strings.xml");
        verifier.assertFilePresent("android-test/application/res/layout/main.xml");
        verifier.assertFilePresent("android-test/application/assets");
        verifier.assertFilePresent("android-test/application/src/main/java/android/archetypes/test/HelloAndroidActivity.java");
        verifier.assertFilePresent("android-test/application-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java");


        Helper.assertContains(new File("target/it/with-test-default/android-test/application/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/application/pom.xml"), "<platform>7</platform>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/application/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/with-test-default/android-test/application/AndroidManifest.xml"), "package=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-default/android-test/application-it/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/application-it/pom.xml"), "<platform>7</platform>");
        Helper.assertContains(new File("target/it/with-test-default/android-test/application-it/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\" />");
        Helper.assertContains(new File("target/it/with-test-default/android-test/application-it/AndroidManifest.xml"), "<instrumentation android:targetPackage=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-default/android-test/application/default.properties"), "target=android-7");
        Helper.assertContains(new File("target/it/with-test-default/android-test/application-it/default.properties"), "target=android-7");

    }

    /**
     * Checks the with-test archetype with the <tt>platform</tt> parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithPlatform() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-with-platform");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=8");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/application");
        verifier.assertFilePresent("android-test/application-it");
        verifier.assertFilePresent("android-test/application/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/application-it/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/application/pom.xml");
        verifier.assertFilePresent("android-test/application-it/pom.xml");

        verifier.assertFilePresent("android-test/application/res/values/strings.xml");
        verifier.assertFilePresent("android-test/application/res/layout/main.xml");
        verifier.assertFilePresent("android-test/application/assets");
        verifier.assertFilePresent("android-test/application/src/main/java/android/archetypes/test/HelloAndroidActivity.java");
        verifier.assertFilePresent("android-test/application-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java");


        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/application/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/application/pom.xml"), "<platform>8</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/application/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/application/AndroidManifest.xml"), "package=\"android.archetypes.test\"");

        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/application-it/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/application-it/pom.xml"), "<platform>8</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/application-it/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\" />");
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/application-it/AndroidManifest.xml"), "<instrumentation android:targetPackage=\"android.archetypes.test\"");

    }

    /**
     * Checks the with-test archetype with the <tt>platform</tt> and <tt>package</tt> parameters.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithPlatformAndPackage() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-with-platform-and-package");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=8");
        cli.add("-Dpackage=foo");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/application");
        verifier.assertFilePresent("android-test/application-it");
        verifier.assertFilePresent("android-test/application/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/application-it/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/application/pom.xml");
        verifier.assertFilePresent("android-test/application-it/pom.xml");

        verifier.assertFilePresent("android-test/application/res/values/strings.xml");
        verifier.assertFilePresent("android-test/application/res/layout/main.xml");
        verifier.assertFilePresent("android-test/application/assets");
        verifier.assertFilePresent("android-test/application/src/main/java/foo/HelloAndroidActivity.java");
        verifier.assertFilePresent("android-test/application-it/src/main/java/foo/test/HelloAndroidActivityTest.java");


        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application/pom.xml"), "<platform>8</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application/AndroidManifest.xml"), "package=\"foo\"");

        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application-it/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application-it/pom.xml"), "<platform>8</platform>");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application-it/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\" />");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application-it/AndroidManifest.xml"), "<instrumentation android:targetPackage=\"foo\"");

        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application/default.properties"), "target=android-8");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/application-it/default.properties"), "target=android-8");
    }


}
