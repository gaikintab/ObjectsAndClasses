package ru.netology

interface Attachment {
    val type: String
}

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String = "",
    val date: Int,
    val thumbHash: String,
    val hasTags: Boolean = false,
    val sizes: Array<Sizes>,
    val width: Int,
    val height: Int
)

data class Sizes(
    val type: String,
    val url: String,
    val width: Int,
    val height: Int
)

class PhotoAttachment(val photo: Photo) : Attachment {
    override val type = "photo"
}

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val image: Array<Image>,
    val firstFrame: Array<FirstFrame>,
    val date: Int,
    val addingDate: Int,
    val views: Int,
    val localViews: Int,
    val comments: Int,
    val player: String,
    val platform: String,
    val canAdd: Boolean,
    val isPrivate: Int = 1,
    val accessKey: String,
    val processing: Int,
    val isFavorite: Boolean,
    val canComment: Boolean,
    val canEdit: Boolean,
    val canLike: Boolean,
    val canRepost: Boolean,
    val canSubscribe: Boolean,
    val canAddToFaves: Boolean,
    val canAttachLink: Boolean,
    val width: Int,
    val height: Int,
    val userId: Int,
    val converting: Boolean,
    val added: Boolean,
    val isSubscribed: Boolean,
    val repeat: Int = 1,
    val type: String = "video",
    val balance: Int,
    val live: Boolean = false,
    val liveStartTime: Int,
    val liveStatus: String,
    val upcoming: Int,
    val spectators: Int,
    val likes: VideoLikes,
    val reposts: Reposts
)

data class Image(
    val height: Int,
    val url: String,
    val width: Int,
    val withPadding: Int = 1
)

data class FirstFrame(
    val height: Int,
    val url: String,
    val width: Int
)

data class VideoLikes(
    val count: Int,
    val userLikes: Boolean
)

data class Reposts(
    val count: Int,
    val wallCount: Int,
    val mailCount: Int,
    val userReposted: Int
)

class VideoAttachment(val video: Video) : Attachment {
    override val type = "video"
}

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHQ: Boolean
)

class AudioAttachment(val audio: Audio) : Attachment {
    override val type = "audio"
}

data class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val url: String,
    val date: Int,
    val type: Int,
    val preview: Preview
)

abstract class Preview {
    abstract val type: String
}

data class PhotoPreview(
    val sizes: Array<Sizes>
) : Preview() {
    override val type = "photo"
}

data class GraffitiPreview(
    val src: String,
    val width: Int,
    val height: Int
) : Preview() {
    override val type = "graffiti"
}

data class AudioPreview(
    val duration: Int,
    val waveform: Array<Int>,
    val linkOgg: String,
    val linkMP3: String
) : Preview() {
    override val type = "audio_message"
}

class FileAttachment(val file: File) : Attachment {
    override val type = "file"
}

data class Geo(
    val type: String,
    val coordinates: Coordinates,
    val place: Place
)

data class Coordinates (
    val latitude: Int,
    val longitude: Int
)

data class Place (
    val type: Int,
    val groupId: Int,
    val groupPhoto: String,
    val checkins: Int,
    val updated: Int,
    val address: Int
)

class GeoAttachment(val geo: Geo) : Attachment {
    override val type = "coordinates"
}
