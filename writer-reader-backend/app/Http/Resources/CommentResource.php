<?php

namespace App\Http\Resources;

use App\Http\Resources\Collections\LikeCollection;
use App\Models\Comment;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

/**
 * Class LikeResource
 * @package App\Http\Resources
 * @mixin Comment
 */
class CommentResource extends JsonResource
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
            'content' => $this->content,
            'user_id' => $this->user->id,
            'user_name' => $this->user->name,
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
            'likes' => $this->likes->count(),
            'is_liked' => $this->likes->contains('user_id', auth()->id()),
        ];
    }
}
