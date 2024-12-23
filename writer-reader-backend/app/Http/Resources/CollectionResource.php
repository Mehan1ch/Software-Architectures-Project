<?php

namespace App\Http\Resources;

use App\Http\Resources\Collections\CommentCollection;
use App\Http\Resources\Collections\LikeCollection;
use App\Http\Resources\Collections\WorkCollection;
use App\Models\Collection;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

/**
 * Class LanguageResource
 * @package App\Http\Resources
 * @mixin Collection
 */
class CollectionResource extends JsonResource
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
            'description' => $this->description,
            'creator_id' => $this->user_id,
            'creator_name' => $this->user->name,
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
            'likes' => $this->likes->count(),
            'is_liked' => $this->likes->contains('user_id', auth()->id()),
            'works' => WorkCollection::make($this->works),
            'comments' => CommentCollection::make($this->comments),
        ];
    }
}
