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

import jp.crudefox.oauth2play2.oauth2.as.validator.CodeValidator;
import jp.crudefox.oauth2play2.oauth2.as.validator.TokenValidator;
import jp.crudefox.oauth2play2.oauth2.bridge.BridgeRequest;
import jp.crudefox.oauth2play2.oauth2.common.OAuth;
import jp.crudefox.oauth2play2.oauth2.common.exception.OAuthProblemException;
import jp.crudefox.oauth2play2.oauth2.common.exception.OAuthSystemException;
import jp.crudefox.oauth2play2.oauth2.common.message.types.ResponseType;
import jp.crudefox.oauth2play2.oauth2.common.utils.OAuthUtils;
import jp.crudefox.oauth2play2.oauth2.common.validators.OAuthValidator;

/**
 *
 *
 *
 */

public class OAuthAuthzRequest extends OAuthRequest {

    public OAuthAuthzRequest(BridgeRequest request) throws OAuthSystemException, OAuthProblemException {
        super(request);
    }

    @Override
    protected OAuthValidator<BridgeRequest> initValidator() throws OAuthProblemException, OAuthSystemException {
        //end user authorization validators
        validators.put(ResponseType.CODE.toString(), CodeValidator.class);
        validators.put(ResponseType.TOKEN.toString(), TokenValidator.class);

        String requestTypeValue = getParam(OAuth.OAUTH_RESPONSE_TYPE);
        if (OAuthUtils.isEmpty(requestTypeValue)) {
            throw OAuthUtils.handleOAuthProblemException("Missing response_type parameter value");
        }
        Class<? extends OAuthValidator<BridgeRequest>> clazz = validators.get(requestTypeValue);
        if (clazz == null) {
            throw OAuthUtils.handleOAuthProblemException("Invalid response_type parameter value");
        }
        return OAuthUtils.instantiateClass(clazz);

    }

    public String getState() {
        return getParam(OAuth.OAUTH_STATE);
    }

    public String getResponseType() {
        return getParam(OAuth.OAUTH_RESPONSE_TYPE);
    }

}
