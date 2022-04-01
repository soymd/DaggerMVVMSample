package io.github.soymd.daggermvvm.network

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class GitRepositoryTest {
    private lateinit var subject: GitRepository
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: String

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        baseUrl = mockWebServer.url("/").url().toString()

        subject = GitRepository(
            Retrofit.Builder(),
            baseUrl
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun get() {
        //language=json
        val json =
            "{\n  \"login\": \"soymd\",\n  \"id\": 483190221,\n  \"node_id\": \"MDQ6VXNlcjQ4MzE5MDIy\",\n  \"avatar_url\": \"https://avatars.githubusercontent.com/u/48319022?v=4\",\n  \"gravatar_id\": \"\",\n  \"url\": \"https://api.github.com/users/soymd\",\n  \"html_url\": \"https://github.com/soymd\",\n  \"followers_url\": \"https://api.github.com/users/soymd/followers\",\n  \"following_url\": \"https://api.github.com/users/soymd/following{/other_user}\",\n  \"gists_url\": \"https://api.github.com/users/soymd/gists{/gist_id}\",\n  \"starred_url\": \"https://api.github.com/users/soymd/starred{/owner}{/repo}\",\n  \"subscriptions_url\": \"https://api.github.com/users/soymd/subscriptions\",\n  \"organizations_url\": \"https://api.github.com/users/soymd/orgs\",\n  \"repos_url\": \"https://api.github.com/users/soymd/repos\",\n  \"events_url\": \"https://api.github.com/users/soymd/events{/privacy}\",\n  \"received_events_url\": \"https://api.github.com/users/soymd/received_events\",\n  \"type\": \"User\",\n  \"site_admin\": false,\n  \"name\": null,\n  \"company\": null,\n  \"blog\": \"\",\n  \"location\": null,\n  \"email\": null,\n  \"hireable\": null,\n  \"bio\": null,\n  \"twitter_username\": null,\n  \"public_repos\": 6,\n  \"public_gists\": 0,\n  \"followers\": 1,\n  \"following\": 2,\n  \"created_at\": \"2019-03-07T11:36:56Z\",\n  \"updated_at\": \"2022-03-23T09:21:53Z\"\n}"
        val mockResponse = MockResponse()
            .addHeader("Content-Type", "application/json")
            .setResponseCode(200)
            .setBody(json)

        mockWebServer.enqueue(mockResponse)

        val expected = GitObject(483190221)

        val response = runBlocking {
            subject.get()
        }

        assertThat(response, equalTo(expected))
    }
}