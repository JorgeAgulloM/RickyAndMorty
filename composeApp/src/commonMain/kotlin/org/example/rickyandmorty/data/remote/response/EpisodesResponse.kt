package org.example.rickyandmorty.data.remote.response

import kotlinx.serialization.Serializable
import org.example.rickyandmorty.domain.model.EpisodeModel
import org.example.rickyandmorty.domain.model.SeasonEpisode
import org.example.rickyandmorty.domain.model.SeasonEpisode.*
import org.example.rickyandmorty.isDesktop
import kotlin.random.Random

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
            videoUrl = if (isDesktop()) getVideoYouTubeUrlFromSeason(season) else getVideoFirebaseUrlFromSeason(season),
            season = season
        )
    }

    private fun getCharacterFromUrl() = characters.map { url -> url.substringAfterLast("/") }

    private fun getVideoYouTubeUrlFromSeason(season: SeasonEpisode): String = when (season) {
        SEASON_1 -> youTubeUrls[0]
        SEASON_2 -> youTubeUrls[1]
        SEASON_3 -> youTubeUrls[2]
        SEASON_4 -> youTubeUrls[3]
        SEASON_5 -> youTubeUrls[4]
        SEASON_6 -> youTubeUrls[5]
        SEASON_7 -> youTubeUrls[6]
        UNKNOWN -> youTubeUrls[Random(youTubeUrls.size - 1).nextInt()]
    }

    private fun getVideoFirebaseUrlFromSeason(season: SeasonEpisode): String = when (season) {
        SEASON_1 -> firebaseUrls[0]
        SEASON_2 -> firebaseUrls[1]
        SEASON_3 -> firebaseUrls[2]
        SEASON_4 -> firebaseUrls[3]
        SEASON_5 -> firebaseUrls[4]
        SEASON_6 -> firebaseUrls[5]
        SEASON_7 -> firebaseUrls[6]
        UNKNOWN -> firebaseUrls[Random(firebaseUrls.size - 1).nextInt()]
    }

    private val youTubeUrls: List<String> = listOf(
        "https://www.youtube.com/embed/BFTSrbB2wII?si=bu_O5VmeXYKMA27x",
        "https://www.youtube.com/embed/y23LCrlGQiQ?si=LxWUqKI9j7qw_6d_",
        "https://www.youtube.com/embed/rLyOul8kau0?si=WDmQbAGSPIJ2NMhI",
        "https://www.youtube.com/embed/hl1U0bxTHbY?si=6cWpZcjHsXXqn-H0",
        "https://www.youtube.com/embed/qbHYYXj2gMc?si=zJs2GjpNPNvSqELp",
        "https://www.youtube.com/embed/jerFRSQW9g8?si=JGEuC_6QzNVd1ZWg",
        "https://www.youtube.com/embed/PkZtVBNkmso?si=prOEyFVT4kKiF4QL"
    )

    private val firebaseUrls: List<String> = listOf(
       "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=f7110f5b-d6b7-416f-b416-37f28052d156",
       "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%202%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=650aaadf-8d51-4313-9bd8-5f267c3c8cb4",
       "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%203%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=091804e6-153c-4cc1-9440-93e169bcd8b9",
       "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%204%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=05cfba16-e53a-4081-871b-a1bc825a3d5c",
       "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%205%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=c7ee4757-e85e-47c0-99a2-ff85d413899b",
       "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%206%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=55f1747d-4505-403c-b493-13df66c269b4",
       "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-app.firebasestorage.app/o/Rick%20and%20Morty%20Season%207%20-%20Official%20Trailer%20-%20360kb.mp4?alt=media&token=8ae869fa-ba7a-4ff9-b87b-bf1b6058b38a"
    )

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
