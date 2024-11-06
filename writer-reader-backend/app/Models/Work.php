<?php

namespace App\Models;

use App\Enums\ModerationEnum;
use Database\Factories\WorkFactory;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\MorphMany;

class Work extends Model
{
    /** @use HasFactory<WorkFactory> */
    use HasFactory;
    use HasUuids;

    protected $fillable = [
        'name',
        'content',
        'creator_id',
        'moderation_status',
        'moderator_id',
        'rating_id',
        'languages_id',
        'created_at',
        'updated_at',
    ];

    protected $casts = [
        'created_at' => 'datetime',
        'updated_at' => 'datetime',
        'moderation_status' => ModerationEnum::class,
    ];

    public function creator(): BelongsTo
    {
        return $this->belongsTo(User::class, 'creator_id');
    }

    public function moderator(): BelongsTo
    {
        return $this->belongsTo(User::class, 'moderator_id');
    }

    public function chapters(): HasMany
    {
        return $this->hasMany(Chapter::class);
    }

    public function collections(): BelongsToMany
    {
        return $this->belongsToMany(Collection::class);
    }

    public function rating(): BelongsTo
    {
        return $this->belongsTo(Rating::class, 'rating_id');
    }

    public function language(): BelongsTo
    {
        return $this->belongsTo(Language::class, 'language_id');
    }

    public function warnings(): BelongsToMany
    {
        return $this->belongsToMany(Warning::class);
    }

    public function tags(): BelongsToMany
    {
        return $this->belongsToMany(Tag::class);
    }

    public function characters(): BelongsToMany
    {
        return $this->belongsToMany(Character::class);
    }

    public function comments(): MorphMany
    {
        return $this->morphMany(Comment::class,'commentable');
    }

    public function likes(): MorphMany
    {
        return $this->morphMany(Like::class,'likeable');
    }
}
