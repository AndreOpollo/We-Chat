package com.example.wechat.features.home.presentation.util

object Images{
    const val CHARLES_LECLERC = "https://cdn.ferrari.com/cms/network/media/img/resize/664ddbcb38b295002043cc52-ferrari-scuderia-2024-charles-leclerc-stats-image-mob-hp?width=750&height=0"
    const val CARLOS_SAINZ = "https://cdn.ferrari.com/cms/network/media/img/resize/664ddb35a3fbde00109a5b43-ferrari-2024-piloti-sainz-cover-desk?width=1440&height=900"
    const val LEWIS_HAM = "https://images.ctfassets.net/1fvlg6xqnm65/4aNxTTOoHJTAhq6yXecYx4/f784b51770d537e1163fdf71a0b565bd/Lewis_Hamilton.png?w=3840&q=75&fm=webp"
    const val KIMI_RAIK = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQg5DUBJKogLVjZSqYFx2Uiv1SsMRGanG-kexs5DElJh_kI-6yk"
    const val MAX_VERST = "https://cdn-3.motorsport.com/images/mgl/6D1XbeV0/s800/max-verstappen-red-bull-racing.jpg"
    const val LANDO_NORRIS = "https://cdn-7.motorsport.com/images/mgl/YEQ1jk1Y/s8/lando-norris-mclaren.jpg"
    const val OSCAR_PIASTRI = "https://cdn-9.motorsport.com/images/mgl/YBe5yNn2/s8/oscar-piastri-mclaren.jpg"
    const val NANDO_ALONSO = "https://cdn-1.motorsport.com/images/mgl/0ZRQbbN0/s8/fernando-alonso-aston-martin-r.jpg"
    const val DANNY_RIC = "https://cdn-3.motorsport.com/images/mgl/2d1ZDk5Y/s8/daniel-ricciardo-racing-bulls.jpg"

}
data class Story(
    val name: String,
    val profile: String,
    val message:String? = null
)

val storyList = listOf(
    Story("Leclerc",Images.CHARLES_LECLERC),
    Story("Lando",Images.LANDO_NORRIS),
    Story("Piastri",Images.OSCAR_PIASTRI),
    Story("Ricciardo",Images.DANNY_RIC),
    Story("Hamilton",Images.LEWIS_HAM),
    Story("Sainz",Images.CARLOS_SAINZ),
    Story("Raikkonen",Images.KIMI_RAIK),
    Story("Verstappen",Images.MAX_VERST),
    Story("Charles",Images.CHARLES_LECLERC),
    Story("Lewis",Images.LEWIS_HAM),
    Story("Alonso",Images.NANDO_ALONSO),


)
val chatList = listOf(
    Story("Leclerc",Images.CHARLES_LECLERC,"Please help find a good helmet?"),
    Story("Lando",Images.LANDO_NORRIS,"Its friday night!!"),
    Story("Piastri",Images.OSCAR_PIASTRI,"Hi"),
    Story("Ricciardo",Images.DANNY_RIC,"Wassup mate?"),
    Story("Hamilton",Images.LEWIS_HAM,"I'm going to Ferrari mahn"),
    Story("Sainz",Images.CARLOS_SAINZ,"Fernando got a penalty"),
    Story("Raikkonen",Images.KIMI_RAIK,"I don't care"),
    Story("Verstappen",Images.MAX_VERST,"It's so unfair"),

)