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
            'user' => UserResource::make($this->user),
            'works' => WorkCollection::make($this->works),
            'likes' => LikeCollection::make($this->likes),
            'comments' => CommentCollection::make($this->comments),
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
        ];
    }
}
