//package com.example.strarterandroid.network.local_network.model
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import java.io.Serializable
//@Entity
//data class GithubReposListRoomModel(
//    @PrimaryKey
//    @ColumnInfo("id") val id: Int,
//    @ColumnInfo("node_id") val nodeId: String,
//    @ColumnInfo("name") val name: String,
//    @ColumnInfo("full_name") val fullName: String,
//    @ColumnInfo("owner") val owner: Owner,
//    @ColumnInfo("private") val private: Boolean,
//    @ColumnInfo("html_url") val htmlUrl: String,
//    @ColumnInfo("description") val description: String?,
//    @ColumnInfo("fork") val fork: Boolean,
//    @ColumnInfo("url") val url: String,
//    @ColumnInfo("archive_url") val archiveUrl: String,
//    @ColumnInfo("assignees_url") val assigneesUrl: String,
//    @ColumnInfo("blobs_url") val blobsUrl: String,
//    @ColumnInfo("branches_url") val branchesUrl: String,
//    @ColumnInfo("collaborators_url") val collaboratorsUrl: String,
//    @ColumnInfo("comments_url") val commentsUrl: String,
//    @ColumnInfo("commits_url") val commitsUrl: String,
//    @ColumnInfo("compare_url") val compareUrl: String,
//    @ColumnInfo("contents_url") val contentsUrl: String,
//    @ColumnInfo("contributors_url") val contributorsUrl: String,
//    @ColumnInfo("deployments_url") val deploymentsUrl: String,
//    @ColumnInfo("downloads_url") val downloadsUrl: String,
//    @ColumnInfo("events_url") val eventsUrl: String,
//    @ColumnInfo("forks_url") val forksUrl: String,
//    @ColumnInfo("git_commits_url") val gitCommitsUrl: String,
//    @ColumnInfo("git_refs_url") val gitRefsUrl: String,
//    @ColumnInfo("git_tags_url") val gitTagsUrl: String,
//    @ColumnInfo("git_url") val gitUrl: String,
//    @ColumnInfo("issue_comment_url") val issueCommentUrl: String,
//    @ColumnInfo("issue_events_url") val issueEventsUrl: String,
//    @ColumnInfo("issues_url") val issuesUrl: String,
//    @ColumnInfo("keys_url") val keysUrl: String,
//    @ColumnInfo("labels_url") val labelsUrl: String,
//    @ColumnInfo("languages_url") val languagesUrl: String,
//    @ColumnInfo("merges_url") val mergesUrl: String,
//    @ColumnInfo("milestones_url") val milestonesUrl: String,
//    @ColumnInfo("notifications_url") val notificationsUrl: String,
//    @ColumnInfo("pulls_url") val pullsUrl: String,
//    @ColumnInfo("releases_url") val releasesUrl: String,
//    @ColumnInfo("ssh_url") val sshUrl: String,
//    @ColumnInfo("stargazers_url") val stargazersUrl: String,
//    @ColumnInfo("statuses_url") val statusesUrl: String,
//    @ColumnInfo("subscribers_url") val subscribersUrl: String,
//    @ColumnInfo("subscription_url") val subscriptionUrl: String,
//    @ColumnInfo("tags_url") val tagsUrl: String,
//    @ColumnInfo("teams_url") val teamsUrl: String,
//    @ColumnInfo("trees_url") val treesUrl: String,
//    @ColumnInfo("hooks_url") val hooksUrl: String
//) : Serializable
//
//data class Owner(
//    @ColumnInfo("login") val login: String,
//    @ColumnInfo("id") val id: Int,
//    @ColumnInfo("node_id") val nodeId: String,
//    @ColumnInfo("avatar_url") val avatarUrl: String,
//    @ColumnInfo("gravatar_id") val gravatarId: String,
//    @ColumnInfo("url") val url: String,
//    @ColumnInfo("html_url") val htmlUrl: String,
//    @ColumnInfo("followers_url") val followersUrl: String,
//    @ColumnInfo("following_url") val followingUrl: String,
//    @ColumnInfo("gists_url") val gistsUrl: String,
//    @ColumnInfo("starred_url") val starredUrl: String,
//    @ColumnInfo("subscriptions_url") val subscriptionsUrl: String,
//    @ColumnInfo("organizations_url") val organizationsUrl: String,
//    @ColumnInfo("repos_url") val reposUrl: String,
//    @ColumnInfo("events_url") val eventsUrl: String,
//    @ColumnInfo("received_events_url") val receivedEventsUrl: String,
//    @ColumnInfo("type") val type: String,
//    @ColumnInfo("site_admin") val siteAdmin: Boolean
//) : Serializable
