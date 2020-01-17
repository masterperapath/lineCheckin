/*
 * Copyright 2019 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
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

package com.linecorp.bot.client;

import java.util.concurrent.CompletableFuture;

import com.linecorp.bot.model.oauth.ChannelAccessTokenException;
import com.linecorp.bot.model.oauth.IssueChannelAccessTokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * An OAuth client that issues or revokes channel access tokens. See {@link LineOAuthClient} and
 * <a href="https://developers.line.biz/en/reference/messaging-api/#issue-channel-access-token">document</a>
 * for detail.
 */
interface LineOAuthService {
    /**
     * Issues a short-lived channel access token. Up to 30 tokens can be issued. If the maximum is exceeded,
     * existing channel access tokens are revoked in the order of when they were first issued.
     * It will return a failed {@link CompletableFuture} with {@link ChannelAccessTokenException} if
     * it has an error during calling the API.
     */
    @FormUrlEncoded
    @POST("v2/oauth/accessToken")
    Call<IssueChannelAccessTokenResponse> issueChannelToken(@Field("grant_type") String grantType,
                                                            @Field("client_id") String clientId,
                                                            @Field("client_secret") String clientSecret);

    /**
     * Revokes a channel access token. It will return a failed {@link CompletableFuture} with
     * {@link ChannelAccessTokenException} if it has an error during calling the API.
     */
    @FormUrlEncoded
    @POST("v2/oauth/revoke")
    Call<Void> revokeChannelToken(@Field("access_token") String accessToken);
}
