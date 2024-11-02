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
            videoUrl = getVideoUrlFromSeason(season),
            season = season
        )
    }

    private fun getCharacterFromUrl() = characters.map { url -> url.substringAfterLast("/") }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String = when (season) {
        SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=f7110f5b-d6b7-416f-b416-37f28052d156"
        SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%202%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=650aaadf-8d51-4313-9bd8-5f267c3c8cb4"
        SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%203%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=091804e6-153c-4cc1-9440-93e169bcd8b9"
        SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%204%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=05cfba16-e53a-4081-871b-a1bc825a3d5c"
        SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%205%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=c7ee4757-e85e-47c0-99a2-ff85d413899b"
        SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%206%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=55f1747d-4505-403c-b493-13df66c269b4"
        SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%207%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=8ae869fa-ba7a-4ff9-b87b-bf1b6058b38a"
        UNKNOWN -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%207%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=8ae869fa-ba7a-4ff9-b87b-bf1b6058b38a"
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
