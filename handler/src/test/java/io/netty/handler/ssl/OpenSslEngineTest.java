/*
 * Copyright 2015 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.handler.ssl;

import org.junit.Test;

import io.netty.handler.ssl.ApplicationProtocolConfig.SelectedListenerFailureBehavior;
import io.netty.handler.ssl.ApplicationProtocolConfig.SelectorFailureBehavior;
import io.netty.handler.ssl.ApplicationProtocolConfig.Protocol;
import static org.junit.Assume.assumeTrue;

public class OpenSslEngineTest extends SSLEngineTest {

    private static final String PREFERRED_APPLICATION_LEVEL_PROTOCOL = "my-protocol-http2";
    private static final String FALLBACK_APPLICATION_LEVEL_PROTOCOL = "my-protocol-http1_1";
    private static final String APPLICATION_LEVEL_PROTOCOL_NOT_COMPATIBLE = "my-protocol-FOO";

    @Test
    public void testNpn() throws Exception {
        assumeTrue(OpenSsl.isAvailable());
        ApplicationProtocolConfig apn = acceptingNegotiator(Protocol.NPN,
                PREFERRED_APPLICATION_LEVEL_PROTOCOL);
        setupHandlers(apn);
        runTest(PREFERRED_APPLICATION_LEVEL_PROTOCOL);
    }

    @Test
    public void testAlpn() throws Exception {
        assumeTrue(OpenSsl.isAvailable());
        ApplicationProtocolConfig apn = acceptingNegotiator(Protocol.ALPN,
                PREFERRED_APPLICATION_LEVEL_PROTOCOL);
        setupHandlers(apn);
        runTest(PREFERRED_APPLICATION_LEVEL_PROTOCOL);
    }

    @Override
    public void testMutualAuthSameCerts() throws Exception {
        assumeTrue(OpenSsl.isAvailable());
        super.testMutualAuthSameCerts();
    }

    @Override
    public void testMutualAuthDiffCerts() throws Exception {
        assumeTrue(OpenSsl.isAvailable());
        super.testMutualAuthDiffCerts();
    }

    @Override
    public void testMutualAuthDiffCertsServerFailure() throws Exception {
        assumeTrue(OpenSsl.isAvailable());
        super.testMutualAuthDiffCertsServerFailure();
    }

    @Override
    public void testMutualAuthDiffCertsClientFailure() throws Exception {
        assumeTrue(OpenSsl.isAvailable());
        super.testMutualAuthDiffCertsClientFailure();
    }

    @Override
    public void testGetCreationTime() throws Exception {
        assumeTrue(OpenSsl.isAvailable());
        super.testGetCreationTime();
    }

    @Override
    public void testSessionInvalidate() throws Exception {
        assumeTrue(OpenSsl.isAvailable());
        super.testSessionInvalidate();
    }

    @Override
    protected SslProvider sslProvider() {
        return SslProvider.OPENSSL;
    }

    protected ApplicationProtocolConfig acceptingNegotiator(ApplicationProtocolConfig.Protocol protocol,
            String... supportedProtocols) {
        return new ApplicationProtocolConfig(protocol,
                SelectorFailureBehavior.NO_ADVERTISE,
                SelectedListenerFailureBehavior.ACCEPT,
                supportedProtocols);
    }
}
