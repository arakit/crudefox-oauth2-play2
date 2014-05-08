/**
 *       Copyright 2010 Newcastle University
 *
 *          http://research.ncl.ac.uk/smart/
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.crudefox.oauth2play2.oauth2.as.request;

import jp.crudefox.oauth2play2.oauth2.as.validator.AuthorizationCodeValidator;
import jp.crudefox.oauth2play2.oauth2.as.validator.ClientCredentialValidator;
import jp.crudefox.oauth2play2.oauth2.as.validator.PasswordValidator;
import jp.crudefox.oauth2play2.oauth2.as.validator.RefreshTokenValidator;
import jp.crudefox.oauth2play2.oauth2.bridge.BridgeRequest;
import jp.crudefox.oauth2play2.oauth2.common.OAuth;
import jp.crudefox.oauth2play2.oauth2.common.exception.OAuthProblemException;
import jp.crudefox.oauth2play2.oauth2.common.exception.OAuthSystemException;
import jp.crudefox.oauth2play2.oauth2.common.message.types.GrantType;
import jp.crudefox.oauth2play2.oauth2.common.utils.OAuthUtils;
import jp.crudefox.oauth2play2.oauth2.common.validators.OAuthValidator;
/**
 *
 *
 *
 */

public class OAuthTokenRequest extends OAuthRequest {


    public OAuthTokenRequest(BridgeRequest request) throws OAuthSystemException, OAuthProblemException {
        super(request);
    }

    @Override
    protected OAuthValidator<BridgeRequest> initValidator() throws OAuthProblemException, OAuthSystemException {
        validators.put(GrantType.PASSWORD.toString(), PasswordValidator.class);
        validators.put(GrantType.CLIENT_CREDENTIALS.toString(), ClientCredentialValidator.class);
        validators.put(GrantType.AUTHORIZATION_CODE.toString(), AuthorizationCodeValidator.class);
        validators.put(GrantType.REFRESH_TOKEN.toString(), RefreshTokenValidator.class);
        String requestTypeValue = getParam(OAuth.OAUTH_GRANT_TYPE);
        if (OAuthUtils.isEmpty(requestTypeValue)) {
            throw OAuthUtils.handleOAuthProblemException("Missing grant_type parameter value");
        }
        Class<? extends OAuthValidator<BridgeRequest>> clazz = validators.get(requestTypeValue);
        if (clazz == null) {
            throw OAuthUtils.handleOAuthProblemException("Invalid grant_type parameter value");
        }
        return OAuthUtils.instantiateClass(clazz);
    }

    public String getPassword() {
        return getParam(OAuth.OAUTH_PASSWORD);
    }

    public String getUsername() {
        return getParam(OAuth.OAUTH_USERNAME);
    }

    public String getRefreshToken() {
        return getParam(OAuth.OAUTH_REFRESH_TOKEN);
    }

    public String getCode() {
        return getParam(OAuth.OAUTH_CODE);
    }

    public String getGrantType() {
        return getParam(OAuth.OAUTH_GRANT_TYPE);
    }

}
