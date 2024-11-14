<?php

namespace App\Models;

use Database\Factories\WorkFactory;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

/**
 * @class Message
 * @package App\Models
 * @property string id
 * @property string content
 * @property int sent_by_id
 * @property int sent_to_id
 * @property string created_at
 * @property string updated_at
 * @property User sender
 * @property User receiver
 */
class Message extends Model
{
    /** @use HasFactory<WorkFactory> */
    use HasFactory;
    use HasUuids;

    protected $fillable = [
        'content',
        'sent_by_id',
        'sent_to_id',
        'created_at',
        'updated_at',
    ];

    protected $casts = [
        'created_at' => 'datetime',
        'updated_at' => 'datetime',
    ];

    public function sender(): BelongsTo
    {
        return $this->belongsTo(User::class, 'sent_by_id');
    }

    public function receiver(): BelongsTo
    {
        return $this->belongsTo(User::class, 'sent_to_id');
    }
}
