package voxy.friend.chat.common.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import voxy.friend.chat.common.model.chat.UserProfileEntity

@Dao
interface UserProfileDao {

    @Query("SELECT * FROM user_profile WHERE userId = :userId")
    fun getUserProfile(userId: String): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE userId = :userId")
    suspend fun getUserProfileOnce(userId: String): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(user: UserProfileEntity)

    @Update
    suspend fun updateUserProfile(user: UserProfileEntity)

    @Delete
    suspend fun deleteUserProfile(user: UserProfileEntity)

    @Query("UPDATE user_profile SET isOnline = :isOnline WHERE userId = :userId")
    suspend fun updateOnlineStatus(userId: String, isOnline: Boolean)

    @Query("UPDATE user_profile SET lastSeen = :timestamp WHERE userId = :userId")
    suspend fun updateLastSeen(userId: String, timestamp: Long)

    @Query("DELETE FROM user_profile WHERE userId = :userId")
    suspend fun deleteUserProfileById(userId: String)

    @Query("DELETE FROM user_profile")
    suspend fun deleteAllUserProfiles()
}