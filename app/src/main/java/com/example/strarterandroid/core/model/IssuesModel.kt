package com.example.strarterandroid.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "issues")
data class IssuesModel(
    @PrimaryKey
    @SerializedName("id") val id: Long?,
    @SerializedName("url") val url: String?,
    @SerializedName("repository_url") val repositoryUrl: String?,
    @SerializedName("labels_url") val labelsUrl: String?,
    @SerializedName("comments_url") val commentsUrl: String?,
    @SerializedName("events_url") val eventsUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("number") val number: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("user") val user: User?,
    @SerializedName("state") val state: String?,
    @SerializedName("locked") val locked: Boolean?,
    @SerializedName("assignee") val assignee: User?,
    @SerializedName("assignees") val assignees: List<User>?,
    @SerializedName("comments") val comments: Int?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("closed_at") val closedAt: String?,
    @SerializedName("author_association") val authorAssociation: String?,
    @SerializedName("active_lock_reason") val activeLockReason: String?,
    @SerializedName("body") val body: String?,
    @SerializedName("reactions") val reactions: Reactions?,
    @SerializedName("timeline_url") val timelineUrl: String?,
    @SerializedName("performed_via_github_app") val performedViaGithubApp: Boolean?,
    @SerializedName("state_reason") val stateReason: String?
)

data class User(
    @SerializedName("login") val login: String?,
    @SerializedName("id") val id: Long?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("gravatar_id") val gravatarId: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("followers_url") val followersUrl: String?,
    @SerializedName("following_url") val followingUrl: String?,
    @SerializedName("gists_url") val gistsUrl: String?,
    @SerializedName("starred_url") val starredUrl: String?,
    @SerializedName("subscriptions_url") val subscriptionsUrl: String?,
    @SerializedName("organizations_url") val organizationsUrl: String?,
    @SerializedName("repos_url") val reposUrl: String?,
    @SerializedName("events_url") val eventsUrl: String?,
    @SerializedName("received_events_url") val receivedEventsUrl: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("site_admin") val siteAdmin: Boolean?
)

data class Reactions(
    @SerializedName("url") val url: String?,
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("+1") val plusOne: Int?,
    @SerializedName("-1") val minusOne: Int?,
    @SerializedName("laugh") val laugh: Int?,
    @SerializedName("hooray") val hooray: Int?,
    @SerializedName("confused") val confused: Int?,
    @SerializedName("heart") val heart: Int?,
    @SerializedName("rocket") val rocket: Int?,
    @SerializedName("eyes") val eyes: Int?
)
