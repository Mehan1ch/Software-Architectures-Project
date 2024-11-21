<?php

namespace App\Models;

use Database\Factories\WorkFactory;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use Illuminate\Database\Eloquent\Relations\HasMany;

/**
 * @class Rating
 * @package App\Models
 * @property string id
 * @property string details
 * @property string created_at
 * @property string updated_at
 * @property Work[] works
 */
class Rating extends Model
{
    /** @use HasFactory<WorkFactory> */
    use HasFactory;
    use HasUuids;

    protected $fillable = [
        'details',
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

        static::deleting(function($rating) {
            //Should be delete but that would delete works, for time saving purposes won't do anything
            //$rating->works()->detach();
        });
    }

    public function works(): HasMany
    {
        return $this->hasMany(Work::class);
    }
}
