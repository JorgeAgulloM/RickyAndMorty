package org.example.rickyandmorty.data.remote.response

import kotlinx.serialization.Serializable
import org.example.rickyandmorty.domain.model.EpisodeModel
import org.example.rickyandmorty.domain.model.SeasonEpisode
import org.example.rickyandmorty.domain.model.SeasonEpisode.*

@Serializable
data class EpisodesResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
) {
    fun toDomain(): EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            characters = getCharacterFromUrl(),
            videoUrl = "",
            season = season
        )
    }

    private fun getCharacterFromUrl() = characters.map { url -> url.substringAfter("/") }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String = when (season) {
        SEASON_1 -> "https://www.youtube.com/watch?v=BFTSrbB2wII&ab_channel=NathanMcKean"
        SEASON_2 -> "https://www.youtube.com/watch?v=y23LCrlGQiQ&ab_channel=spideyfan300"
        SEASON_3 -> "https://www.youtube.com/watch?v=rLyOul8kau0&ab_channel=SeriesTrailerMP"
        SEASON_4 -> "https://www.youtube.com/watch?v=hl1U0bxTHbY&ab_channel=IGN"
        SEASON_5 -> "https://www.youtube.com/watch?v=qbHYYXj2gMc&ab_channel=TVPromos"
        SEASON_6 -> "https://www.youtube.com/watch?v=jerFRSQW9g8&ab_channel=RottenTomatoesTV"
        SEASON_7 -> "https://www.youtube.com/watch?v=PkZtVBNkmso&ab_channel=RottenTomatoesTV"
        UNKNOWN -> "https://www.youtube.com/watch?v=PkZtVBNkmso&ab_channel=RottenTomatoesTV"
    }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode = when {
        episode.startsWith("S01") -> SEASON_1
        episode.startsWith("S02") -> SEASON_2
        episode.startsWith("S03") -> SEASON_3
        episode.startsWith("S04") -> SEASON_4
        episode.startsWith("S05") -> SEASON_5
        episode.startsWith("S06") -> SEASON_6
        episode.startsWith("S07") -> SEASON_7
        else -> UNKNOWN
    }
}
