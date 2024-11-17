<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;
use Database\Factories\UserFactory;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

/**
 * @class User
 * @package App\Models
 * @property string id
 * @property string name
 * @property string email
 * @property string email_verified_at
 * @property string password
 * @property string remember_token
 * @property string created_at
 * @property string updated_at
 * @property Work[] works
 * @property Collection[] collections
 * @property Comment[] comments
 * @property Like[] likes
 * @property Message[] sentMessages
 * @property Message[] receivedMessages
 * @property Work[] moderatedWorks
 */
class User extends Authenticatable
{
    /** @use HasFactory<UserFactory> */
    use HasFactory, Notifiable, HasUuids;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'name',
        'email',
    ];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * Get the attributes that should be cast.
     *
     * @return array<string, string>
     */
    protected function casts(): array
    {
        return [
            'email_verified_at' => 'datetime',
            'password' => 'hashed',
        ];
    }

    protected static function boot():void {
        parent::boot();
        static::deleting(function($user) {
            $user->works()->delete();
            $user->collections()->delete();
            $user->comments()->delete();
            $user->likes()->delete();
            /* These should be deleted by the user's deletion */
            //$user->sentMessages()->delete();
            //$user->receivedMessages()->delete();
            //$user->moderatedWorks()->delete();
        });
    }

    public function works(): HasMany
    {
        return $this->hasMany(Work::class);
    }

    public function collections(): HasMany
    {
        return $this->hasMany(Collection::class);
    }

    public function comments(): HasMany
    {
        return $this->hasMany(Comment::class);
    }

    public function likes(): HasMany
    {
        return $this->hasMany(Like::class);
    }

    public function sentMessages(): HasMany
    {
        return $this->hasMany(Message::class);
    }

    public function receivedMessages(): HasMany
    {
        return $this->hasMany(Message::class);
    }

    public function moderatedWorks(): HasMany
    {
        return $this->hasMany(Work::class, 'moderator_id');
    }
}
