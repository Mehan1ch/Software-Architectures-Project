<?php

namespace App\Models;

use Database\Factories\WorkFactory;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\MorphTo;

/**
 * @class Like
 * @package App\Models
 * @property string id
 * @property int user_id
 * @property string likeable_id
 * @property string likeable_type
 * @property string created_at
 * @property string updated_at
 * @property User user
 */
class Like extends Model
{
    /** @use HasFactory<WorkFactory> */
    use HasFactory;
    use HasUuids;

    protected $fillable = [
        'user_id',
        'likeable_id',
        'likeable_type',
        'created_at',
        'updated_at',
    ];

    protected $casts = [
        'created_at' => 'datetime',
        'updated_at' => 'datetime',
    ];

    protected static function boot(): void
    {
        parent::boot();

        static::deleting(function($like) {
            $like->user()->dissociate();
            $like->likeable()->dissociate();
        });
    }

    public function user(): BelongsTo
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    public function likeable(): MorphTo
    {
        return $this->morphTo();
    }
}
