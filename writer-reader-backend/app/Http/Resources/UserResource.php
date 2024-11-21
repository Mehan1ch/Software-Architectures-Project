<?php

namespace App\Http\Resources;

use App\Http\Resources\Collections\CollectionCollection;
use App\Http\Resources\Collections\CommentCollection;
use App\Http\Resources\Collections\LikeCollection;
use App\Http\Resources\Collections\MessageCollection;
use App\Http\Resources\Collections\WorkCollection;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

/**
 * Class WarningResource
 * @package App\Http\Resources
 * @mixin User
 */
class UserResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'email' => $this->email,
            'email_verified_at' => $this->email_verified_at,
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
            'works' => WorkCollection::make($this->works),
            'collections' => CollectionCollection::make($this->collections),
            'comments' => CommentCollection::make($this->comments),
            'likes' => LikeCollection::make($this->likes),
            'sentMessages' => MessageCollection::make($this->sentMessages),
            'receivedMessages' => MessageCollection::make($this->receivedMessages),
            'moderatedWorks' => WorkCollection::make($this->moderatedWorks),
        ];
    }
}
