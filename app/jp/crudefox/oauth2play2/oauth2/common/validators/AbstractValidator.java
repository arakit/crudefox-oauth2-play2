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

package jp.crudefox.oauth2play2.oauth2.common.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.crudefox.oauth2play2.oauth2.bridge.BridgeRequest;
import jp.crudefox.oauth2play2.oauth2.common.OAuth;
import jp.crudefox.oauth2play2.oauth2.common.exception.OAuthProblemException;
import jp.crudefox.oauth2play2.oauth2.common.utils.OAuthUtils;
import scala.Option;

/**
 *
 *
 *
 */
//todo add client secret in header, sect 2.1
public abstract class AbstractValidator<T extends BridgeRequest> implements OAuthValidator<T> {

    protected List<String> requiredParams = new ArrayList<String>();
    protected Map<String, String[]> optionalParams = new HashMap<String, String[]>();
    protected List<String> notAllowedParams = new ArrayList<String>();


    @Override
    public void validateMethod(T request) throws OAuthProblemException {
        if (!request.method().equals(OAuth.HttpMethod.POST)) {
            throw OAuthUtils.handleOAuthProblemException("Method not set to POST.");
        }
    }

    @Override
    public void validateContentType(T request) throws OAuthProblemException {


    	Option<String> o = request.headers().get(OAuth.HeaderType.CONTENT_TYPE);
        String contentType = o.isEmpty()?null:o.get();

        final String expectedContentType = OAuth.ContentType.URL_ENCODED;
        if (!OAuthUtils.hasContentType(contentType, expectedContentType)) {
            throw OAuthUtils.handleBadContentTypeException(expectedContentType);
        }
    }

    @Override
    public void validateRequiredParameters(T request) throws OAuthProblemException {
        Set<String> missingParameters = new HashSet<String>();
        for (String requiredParam : requiredParams) {
        	Option<String> o = request.getQueryString(requiredParam);
        	String val = o.isEmpty()?null:o.get();

        	try {
				if (val == null) {
					play.api.mvc.AnyContent a = (play.api.mvc.AnyContent) (request
							.body());

					if (a.asFormUrlEncoded().isDefined()) {
						scala.collection.immutable.Map<String, scala.collection.Seq<String>> amap = a
								.asFormUrlEncoded().get();
						Option<scala.collection.Seq<String>> ao = amap
								.get(requiredParam);
						String aos = ao.get().apply(0);
						val = aos;
					}
				}
    		} catch (Exception anyE) {
    			// do nothing
    		}



            if (OAuthUtils.isEmpty(val)) {
                missingParameters.add(requiredParam);
            }
        }
        if (!missingParameters.isEmpty()) {
            throw OAuthUtils.handleMissingParameters(missingParameters);
        }
    }

    @Override
    public void validateOptionalParameters(T request) throws OAuthProblemException {

        Set<String> missingParameters = new HashSet<String>();

        for (Map.Entry<String, String[]> requiredParam : optionalParams.entrySet()) {
            String paramName = requiredParam.getKey();
            Option<String> o = request.getQueryString(paramName);
            String val = o.isEmpty()?null:o.get();
            if (!OAuthUtils.isEmpty(val)) {
                String[] dependentParams = requiredParam.getValue();
                if (!OAuthUtils.hasEmptyValues(dependentParams)) {
                    for (String dependentParam : dependentParams) {
                        val = o.isEmpty()?null:o.get();
                        if (OAuthUtils.isEmpty(val)) {
                            missingParameters.add(dependentParam);
                        }
                    }
                }
            }
        }

        if (!missingParameters.isEmpty()) {
            throw OAuthUtils.handleMissingParameters(missingParameters);
        }
    }

    @Override
    public void validateNotAllowedParameters(T request) throws OAuthProblemException {
        List<String> notAllowedParameters = new ArrayList<String>();
        for (String requiredParam : notAllowedParams) {
        	Option<String> o = request.getQueryString(requiredParam);
            String val = o.isEmpty()?null:o.get();
            if (!OAuthUtils.isEmpty(val)) {
                notAllowedParameters.add(requiredParam);
            }
        }
        if (!notAllowedParameters.isEmpty()) {
            throw OAuthUtils.handleNotAllowedParametersOAuthException(notAllowedParameters);
        }
    }

    @Override
    public void performAllValidations(T request) throws OAuthProblemException {
        this.validateContentType(request);
        this.validateMethod(request);
        this.validateRequiredParameters(request);
        this.validateOptionalParameters(request);
        this.validateNotAllowedParameters(request);
    }
}
