package com.example.strarterandroid.network.local_network.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issues")
data class IssuesRoomModel(
    @PrimaryKey
    @ColumnInfo("id") val id: Long?,
    @ColumnInfo("url") val url: String?,
    @ColumnInfo("repository_url") val repositoryUrl: String?,
    @ColumnInfo("labels_url") val labelsUrl: String?,
    @ColumnInfo("comments_url") val commentsUrl: String?,
    @ColumnInfo("events_url") val eventsUrl: String?,
    @ColumnInfo("html_url") val htmlUrl: String?,
    @ColumnInfo("node_id") val nodeId: String?,
    @ColumnInfo("number") val number: Int?,
    @ColumnInfo("title") val title: String?,
    @ColumnInfo("user") val user: User?,
    @ColumnInfo("state") val state: String?,
    @ColumnInfo("locked") val locked: Boolean?,
    @ColumnInfo("assignee") val assignee: User?,
    @ColumnInfo("assignees") val assignees: List<User>?,
    @ColumnInfo("comments") val comments: Int?,
    @ColumnInfo("created_at") val createdAt: String?,
    @ColumnInfo("updated_at") val updatedAt: String?,
    @ColumnInfo("closed_at") val closedAt: String?,
    @ColumnInfo("author_association") val authorAssociation: String?,
    @ColumnInfo("active_lock_reason") val activeLockReason: String?,
    @ColumnInfo("body") val body: String?,
    @ColumnInfo("reactions") val reactions: Reactions?,
    @ColumnInfo("timeline_url") val timelineUrl: String?,
    @ColumnInfo("performed_via_github_app") val performedViaGithubApp: Boolean?,
    @ColumnInfo("state_reason") val stateReason: String?
)

data class User(
    @ColumnInfo("login") val login: String?,
    @ColumnInfo("id") val id: Long?,
    @ColumnInfo("node_id") val nodeId: String?,
    @ColumnInfo("avatar_url") val avatarUrl: String?,
    @ColumnInfo("gravatar_id") val gravatarId: String?,
    @ColumnInfo("url") val url: String?,
    @ColumnInfo("html_url") val htmlUrl: String?,
    @ColumnInfo("followers_url") val followersUrl: String?,
    @ColumnInfo("following_url") val followingUrl: String?,
    @ColumnInfo("gists_url") val gistsUrl: String?,
    @ColumnInfo("starred_url") val starredUrl: String?,
    @ColumnInfo("subscriptions_url") val subscriptionsUrl: String?,
    @ColumnInfo("organizations_url") val organizationsUrl: String?,
    @ColumnInfo("repos_url") val reposUrl: String?,
    @ColumnInfo("events_url") val eventsUrl: String?,
    @ColumnInfo("received_events_url") val receivedEventsUrl: String?,
    @ColumnInfo("type") val type: String?,
    @ColumnInfo("site_admin") val siteAdmin: Boolean?
)

data class Reactions(
    @ColumnInfo("url") val url: String?,
    @ColumnInfo("total_count") val totalCount: Int?,
    @ColumnInfo("+1") val plusOne: Int?,
    @ColumnInfo("-1") val minusOne: Int?,
    @ColumnInfo("laugh") val laugh: Int?,
    @ColumnInfo("hooray") val hooray: Int?,
    @ColumnInfo("confused") val confused: Int?,
    @ColumnInfo("heart") val heart: Int?,
    @ColumnInfo("rocket") val rocket: Int?,
    @ColumnInfo("eyes") val eyes: Int?
)
