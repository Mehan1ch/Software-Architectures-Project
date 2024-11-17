<?php

namespace App\Models;

use App\Enums\ModerationEnum;
use App\Exceptions\StateMachineException;
use Database\Factories\WorkFactory;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\MorphMany;


/**
 * @class Work
 * @package App\Models
 * @property string id
 * @property string title
 * @property string content
 * @property int creator_id
 * @property ModerationEnum moderation_status
 * @property int moderator_id
 * @property int rating_id
 * @property int language_id
 * @property string created_at
 * @property string updated_at
 * @property User creator
 * @property User moderator
 * @property Chapter[] chapters
 * @property Collection[] collections
 * @property Rating rating
 * @property Language language
 * @property Warning[] warnings
 * @property Tag[] tags
 * @property Character[] characters
 * @property Category[] categories
 * @property Comment[] comments
 * @property Like[] likes
 */
class Work extends Model
{
    /** @use HasFactory<WorkFactory> */
    use HasFactory;
    use HasUuids;

    protected $fillable = [
        'title',
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

    protected static function boot(): void
    {
        parent::boot();

        static::creating(function (self $work) {
            $work->moderation_status = ModerationEnum::PENDING;
        });


        static::updating(function (self $work) {
            if (! $work->isDirty('moderation_status')) {
                return;
            }

            /** @var ModerationEnum $status */
            $status = $work->getOriginal('moderation_status');
            if ($status->ableToTransferTo($work->moderation_status)) {
                throw new StateMachineException("[{$status->name}] cannot transfer into [{$work->moderation_status->name}].");
            }

            // Email notification to the creator if the new status is EDIT_REQUESTED or APPROVED
            if ($work->moderation_status === ModerationEnum::EDIT_REQUESTED || $work->moderation_status === ModerationEnum::APPROVED) {
                // TODO: Send email to the creator
                //Tip: Gmail SMTP server is a good option for development, just need to create a random google account, and an app password, limitation: 500 emails per day
                //See: https://www.iankumu.com/blog/laravel-send-emails/
            }
        });

        static::deleting(function($work) {
            $work->chapters()->delete();
            $work->collections()->detach();
            $work->warnings()->detach();
            $work->tags()->detach();
            $work->characters()->detach();
            $work->categories()->detach();
            $work->comments()->delete();
            $work->likes()->delete();
        });
    }

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
        return $this->belongsToMany(Collection::class)->withTimestamps();
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
        return $this->belongsToMany(Warning::class)->withTimestamps();
    }

    public function tags(): BelongsToMany
    {
        return $this->belongsToMany(Tag::class)->withTimestamps();
    }

    public function characters(): BelongsToMany
    {
        return $this->belongsToMany(Character::class)->withTimestamps();
    }

    public function categories(): BelongsToMany
    {
        return $this->belongsToMany(Category::class)->withTimestamps();
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
