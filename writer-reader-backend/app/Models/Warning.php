<?php

namespace App\Models;

use Database\Factories\WorkFactory;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

/**
 * @class Warning
 * @package App\Models
 * @property string id
 * @property string details
 * @property string created_at
 * @property string updated_at
 * @property Work[] works
 */
class Warning extends Model
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

        static::deleting(function($warning) {
            $warning->works()->detach();
        });
    }

    public function works(): BelongsToMany
    {
        return $this->belongsToMany(Work::class)->withTimestamps();
    }

}
