/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0.
 */

description = "AWS Region Support"
extra["displayName"] = "Software :: AWS :: Kotlin SDK :: Regions"
extra["moduleName"] = "aws.sdk.kotlin.runtime.regions"

val smithyKotlinVersion: String by project

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":client-runtime:aws-client-rt"))
                implementation("software.aws.smithy.kotlin:logging:$smithyKotlinVersion")
            }
        }
        commonTest {
            dependencies {
                implementation(project(":client-runtime:testing"))
            }
        }
    }
}

