/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0.
 */

package aws.sdk.kotlin.codegen.protocols.middleware

import aws.sdk.kotlin.codegen.AwsKotlinDependency
import software.amazon.smithy.kotlin.codegen.core.KotlinWriter
import software.amazon.smithy.kotlin.codegen.model.buildSymbol
import software.amazon.smithy.kotlin.codegen.model.namespace
import software.amazon.smithy.kotlin.codegen.rendering.protocol.ProtocolGenerator
import software.amazon.smithy.kotlin.codegen.rendering.protocol.ProtocolMiddleware

/**
 * HTTP client interceptor that resolves service endpoints for a single service
 */
class EndpointResolverMiddleware(private val ctx: ProtocolGenerator.GenerationContext) : ProtocolMiddleware {
    override val name: String = "ServiceEndpointResolver"
    override fun addImportsAndDependencies(writer: KotlinWriter) {
        val resolverFeatureSymbol = buildSymbol {
            name = "ServiceEndpointResolver"
            namespace(AwsKotlinDependency.AWS_CLIENT_RT_HTTP, subpackage = "middleware")
        }

        // generated symbol
        val defaultResolverSymbol = buildSymbol {
            name = "DefaultEndpointResolver"
            namespace = "${ctx.settings.pkg.name}.internal"
        }

        writer.addImport(resolverFeatureSymbol)
        writer.addImport(defaultResolverSymbol)
    }

    override fun renderConfigure(writer: KotlinWriter) {
        writer.write("serviceId = ServiceId")
        writer.write("resolver = config.endpointResolver ?: DefaultEndpointResolver()")
    }
}