package hu.bme.aut.android.writer_reader_client.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Register : Screen("register")
  //  object Profile : Screen("profile")
    object WorkDetails : Screen("work_detail/{workId}"){
        fun passWorkId(workId: String) = "work_detail/$workId"
    }
    object ReadWork : Screen("read_work/{workId}"){
        fun passWorkId(workId: String) = "read_work/$workId"
    }
    object UserList : Screen("user_list")
    object Conversation : Screen("conversation/{userId}"){
        fun passUserId(userId: String) = "conversation/$userId"
    }
}
